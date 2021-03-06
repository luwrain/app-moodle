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

import org.luwrain.io.moodle.model.DB;
import org.luwrain.io.moodle.model.MDroidNotification;
import org.luwrain.io.moodle.model.MoodleMessage;
import org.luwrain.io.moodle.model.MoodleMessages;
import org.luwrain.io.moodle.model.MoodleSiteInfo;
import org.luwrain.io.moodle.rest.MoodleRestMessage;

import java.util.List;

public class MessageSyncTask {
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
	public MessageSyncTask(String mUrl, String token, long siteid) {
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
	public MessageSyncTask(String mUrl, String token, long siteid,
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
	 * Sync all messages sent / received by a user (current user typically)
	 * 
	 * @param userid
	 *            userid of the current site user.
	 * @return syncStatus
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public Boolean syncMessages(int userid) {
		MoodleRestMessage mrm = new MoodleRestMessage(mUrl, token);
		return saveMessages(mrm.getMessages(userid, 0, 0))
				&& saveMessages(mrm.getMessages(userid, 0, 1))
				&& saveMessages(mrm.getMessages(0, userid, 0))
				&& saveMessages(mrm.getMessages(0, userid, 1));
	}

	private Boolean saveMessages(MoodleMessages moodleMessages) {

		/** Error checking **/
		// Some network or encoding issue.
		if (moodleMessages == null) {
			error = "Network issue!";
			return false;
		}

		// Moodle exception
		if (moodleMessages.getErrorcode() != null) {
			error = moodleMessages.getErrorcode();
			// No additional debug info as that needs context
			return false;
		}

		List<MoodleMessage> mMessages = moodleMessages.getMessages();
		// Warnings are not being handled
		List<MoodleMessage> dbMessages;
		MoodleMessage message = new MoodleMessage();

		// Get site info - used for notification setting
		MoodleSiteInfo site = DB.findById(MoodleSiteInfo.class,
				siteid);
		int currentUserid = (site != null) ? site.getUserid() : 0;

		if (mMessages != null)
			for (int i = 0; i < mMessages.size(); i++) {
				message = mMessages.get(i);
				message.setSiteid(siteid);
				/*
				 * -TODO- Improve this search with only Sql operation
				 */
				dbMessages = DB.find(MoodleMessage.class,
						"messageid = ? and siteid = ?", String.valueOf(message.getMessageid())
								, String.valueOf(siteid));
				if (!dbMessages.isEmpty())
					message.setId(dbMessages.get(0).getId());
				// set notifications if enabled
				else if (notification
						&& message.getUseridfrom() != currentUserid) {
					new MDroidNotification(
							siteid,
							MDroidNotification.TYPE_MESSAGE,
							"New message from " + message.getUserfromfullname(),
							message.getText(), 1, message.getUseridfrom())
							.save();
					notificationcount++;
				}
				message.save();
			}

		return true;
	}

}
