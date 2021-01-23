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

package org.luwrain.io.moodle.model;

//import com.orm.SugarRecord;
//import com.orm.dsl.Ignore;

public class MDroidNotification //extends SugarRecord<MDroidNotification>
{

	//@Ignore
	public static final int TYPE_COURSE_CONTENT = 1;

	//@Ignore
	public static final int TYPE_FORUM = 2;

	//@Ignore
	public static final int TYPE_FORUM_TOPIC = 3;

	//@Ignore
	public static final int TYPE_FORUM_REPLY = 4;

	//@Ignore
	public static final int TYPE_MESSAGE = 5;

	//@Ignore
	public static final int TYPE_PARTICIPANT = 6;

	//@Ignore
	public static final int TYPE_CONTACT = 7;

	//@Ignore
	public static final int TYPE_EVENT = 8;

	int notificationtype;
	String title;
	String content;
	int count;
	Boolean read;
	int extras;

	// relational fields
	long siteid;

	public MDroidNotification() {
		this.read = false;
		this.count = 1;
	}

	/**
	 * @param siteid
	 *            Moodle siteid of the notification
	 */
	public MDroidNotification(long siteid) {
		this.siteid = siteid;
		this.read = false;
		this.count = 1;
	}

	/**
	 * @param siteid
	 *            Moodle siteid of the notification
	 * @param type
	 *            Type of notification
	 * @param title
	 *            Title of notification
	 * @param content
	 *            Content or description of the notification
	 */
	public MDroidNotification(long siteid, int type, String title,
			String content) {
		this.siteid = siteid;
		this.notificationtype = type;
		this.title = title;
		this.content = content;
		this.count = 1;
	}

	/**
	 * @param siteid
	 *            Moodle siteid of the notification
	 * @param type
	 *            Type of notification
	 * @param title
	 *            Title of notification
	 * @param content
	 *            Content or description of the notification
	 * @param count
	 *            notification count
	 */
	public MDroidNotification(long siteid, int type, String title,
			String content, int count) {
		this.siteid = siteid;
		this.notificationtype = type;
		this.title = title;
		this.content = content;
		this.count = count;
		this.read = false;
	}

	/**
	 * @param siteid
	 *            Moodle siteid of the notification
	 * @param type
	 *            Type of notification
	 * @param title
	 *            Title of notification
	 * @param content
	 *            Content or description of the notification
	 * @param count
	 *            notification count
	 * @param extras
	 *            Extras for the notifications. This extras is a bundle param to
	 *            be for intents to handle notification.
	 */
	public MDroidNotification(long siteid, int type, String title,
			String content, int count, int extras) {
		this.siteid = siteid;
		this.notificationtype = type;
		this.title = title;
		this.content = content;
		this.count = count;
		this.read = false;
		this.extras = extras;
	}

	/**
	 * Get the type of notification. Possible types can be check from TYPE_ om
	 * this class.
	 * 
	 * @return type
	 */
	public int getType() {
		return notificationtype;
	}

	/**
	 * Set the notification type. Possible types can be check from TYPE_ om this
	 * class.
	 * 
	 * @param type
	 */
	public void setType(int type) {
		this.notificationtype = type;
	}

	/**
	 * Get notification title
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set notification title
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get notification content
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Set notification content
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Get notification count (if any)
	 * 
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Set notification count
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Get if notification is read
	 * 
	 * @return true if read
	 */
	public Boolean getRead() {
		return read;
	}

	/**
	 * Set read status of notification
	 * 
	 * @param read
	 */
	public void setRead(Boolean read) {
		this.read = read;
	}

	/**
	 * Get siteid of the notification
	 * 
	 * @return
	 */
	public long getSiteid() {
		return siteid;
	}

	/**
	 * Set siteid of the notification
	 * 
	 * @param siteid
	 */
	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}

	/**
	 * Get extras for the notifications. This extras is a bundle param to be for
	 * intents to handle notification.
	 * 
	 * @return notification extras
	 */
	public int getExtras() {
		return extras;
	}

	/**
	 * Set extras for the notifications. This extras is a bundle param to be for
	 * intents to handle notification.
	 * 
	 * @param extras
	 */
	public void setExtras(int extras) {
		this.extras = extras;
	}


    public void save()
    {
    }
}
