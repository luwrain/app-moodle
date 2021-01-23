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
import org.luwrain.io.moodle.rest.MoodleRestUser;

import java.util.List;

public class UserSyncTask {
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
	public UserSyncTask(String mUrl, String token, long siteid) {
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
	public UserSyncTask(String mUrl, String token, long siteid,
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
	 * Sync all topics in a discussion.
	 * 
	 * @return syncStatus
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public Boolean syncUsers(int courseid) {
		MoodleRestUser mru = new MoodleRestUser(mUrl, token);
		List<MoodleUser> mUsers = mru.getUsers(courseid);

		/** Error checking **/
		// Some network or encoding issue.
		if (mUsers == null || mUsers.isEmpty()) {
			error = "No users found!";
			return false;
		}

		List<MoodleUser> dbUsers;
		MoodleUser mUser = new MoodleUser();
		for (int i = 0; i < mUsers.size(); i++) {
			mUser = mUsers.get(i);
			mUser.setSiteid(siteid);
			mUser.setCourseid(courseid);

			dbUsers = DB.find(MoodleUser.class,
					"userid = ? and siteid = ? and courseid = ?",
					String.valueOf(mUser.getUserid()), String.valueOf(siteid), String.valueOf(courseid));
			if (!dbUsers.isEmpty())
				mUser.setId(dbUsers.get(0).getId());
			// set notifications if enabled
			else if (notification) {
				List<Course> dbCourses = DB.find(
						Course.class, "courseid = ? and siteid = ?",
						String.valueOf(siteid), String.valueOf(courseid));
				Course course = (dbCourses != null && !dbCourses.isEmpty()) ? dbCourses
						.get(0) : null;

				if (course != null) {
					new MDroidNotification(siteid,
							MDroidNotification.TYPE_PARTICIPANT,
							"New people joined " + course.getShortname(),
							mUser.getFullname() + " joined "
									+ course.getFullName(), 1, courseid).save();
					notificationcount++;
				}
			}
			mUser.save();
		}

		return true;
	}
}
