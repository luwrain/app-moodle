/*
   Copyright 2020-2021 Michael Pozhidaev <msp@luwrain.org>
   Copyright 2012-2016 Praveen Kumar Pendyala <praveendath92@gmail.com>

   This file is part of LUWRAIN.

   LUWRAIN is free software; you can redistribute it and/or
   modify it under the terms of the GNU General Public
   License as published by the Free Software Foundation; either
   version 3 of the License, or (at your option) any later version.

   LUWRAIN is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   General Public License for more details.
*/

package org.luwrain.io.moodle.task;

import org.luwrain.io.moodle.model.*;
import org.luwrain.io.moodle.rest.MoodleRestEvent;

import java.util.ArrayList;
import java.util.List;

public class EventSyncTask {
	String mUrl;
	String token;
	long siteid;

	String error;
	Boolean notification;
	int notificationcount;

	/**
	 * 
	 * @param mUrl
	 * @param token
	 * @param siteid
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public EventSyncTask(String mUrl, String token, long siteid) {
		this.mUrl = mUrl;
		this.token = token;
		this.siteid = siteid;
		this.notification = false;
		this.notificationcount = 0;
	}

	/**
	 * 
	 * @param mUrl
	 * @param token
	 * @param siteid
	 * @param notification
	 *            If true, sets notifications for new contents
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public EventSyncTask(String mUrl, String token, long siteid,
			Boolean notification) {
		this.mUrl = mUrl;
		this.token = token;
		this.siteid = siteid;
		this.notification = notification;
		this.notificationcount = 0;
	}

	/**
	 * Get the notifications count. Notifications should be enabled during
	 * Object instantiation.
	 * 
	 * @return notificationcount
	 */
	public int getNotificationcount() {
		return notificationcount;
	}

	/**
	 * Sync all the events of a course. This will also sync user and site events
	 * whose scope is outside course.
	 * 
	 * @return syncStatus
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public Boolean syncEvents(int courseid) {
		ArrayList<String> courseids = new ArrayList<>();
		courseids.add(courseid + "");
		return syncEvents(courseids);
	}

	/**
	 * Sync all the events in the list of courses. This will also sync user and
	 * site events whose scope is outside courses.
	 * 
	 * @return syncStatus
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public Boolean syncEvents(ArrayList<String> courseids) {
		MoodleRestEvent mre = new MoodleRestEvent(mUrl, token);
		MoodleEvents mEvents = mre.getEventsForIds(courseids,
				MoodleRestEvent.ID_TYPE_COURSE, true, true);

		/** Error checking **/
		// Some network or encoding issue.
		if (mEvents == null) {
			error = "Network issue!";
			return false;
		}

		// Moodle exception
		if (mEvents.getErrorcode() != null) {
			error = mEvents.getErrorcode();
			// No additional debug info as that needs context
			return false;
		}

		ArrayList<MoodleEvent> events = mEvents.getEvents();
		// Warnings are not being handled
		List<MoodleEvent> dbEvents;
		List<Course> dbCourses;
		MoodleEvent event = new MoodleEvent();

		if (events != null)
			for (int i = 0; i < events.size(); i++) {
				event = events.get(i);
				event.setSiteid(siteid);

				dbEvents = DB.find(MoodleEvent.class,
						"eventid = ? and siteid = ?", String.valueOf(event.getEventid()),
						String.valueOf(siteid));
				dbCourses = DB.find(Course.class,
						"courseid = ? and siteid = ?",
						String.valueOf(event.getCourseid()), String.valueOf(siteid));
				if (!dbCourses.isEmpty())
					event.setCoursename(dbCourses.get(0).getShortname());
				if (!dbEvents.isEmpty())
					event.setId(dbEvents.get(0).getId());

				// set notifications if enabled
				else if (notification) {
					new MDroidNotification(siteid,
							MDroidNotification.TYPE_EVENT, "New events in "
									+ event.getCoursename(),
							"New event titled " + event.getName(), 1,
							event.getCourseid()).save();
					notificationcount++;
				}

				event.save();
			}

		return true;
	}

}
