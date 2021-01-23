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

import com.google.gson.annotations.SerializedName;
//import com.orm.SugarRecord;

public class MoodleEvent //extends SugarRecord<MoodleEvent> {
{
	// since id is a reserved field in SugarRecord
	@SerializedName("id")
	int eventid;

	@SerializedName("name")
	String name;

	@SerializedName("description")
	String description;

	@SerializedName("format")
	int format;

	@SerializedName("courseid")
	int courseid;

	@SerializedName("groupid")
	int groupid;

	@SerializedName("userid")
	int userid;

	@SerializedName("repeatid")
	int repeatid;

	@SerializedName("modulename")
	String modulename;

	@SerializedName("instance")
	int instance;

	@SerializedName("eventtype")
	String eventtype;

	@SerializedName("timestart")
	int timestart;

	@SerializedName("timeduration")
	int timeduration;

	@SerializedName("visible")
	int visible;

	@SerializedName("uuid")
	String uuid;

	@SerializedName("sequence")
	int sequence;

	@SerializedName("timemodified")
	int timemodified;

	@SerializedName("subscriptionid")
	int subscriptionid;

	// Error fields are required only for Events instead

	// Relational and other fields
	long siteid;
	String coursename;

	/**
	 * Get coursename of the event
	 * 
	 * @return
	 */
	public String getCoursename() {
		return coursename;
	}

	/**
	 * Set coursename of the event
	 * 
	 * @param coursename
	 */
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	/**
	 * get the site id of this course
	 * 
	 * @param siteid
	 */
	public long getSiteid() {
		return siteid;
	}

	/**
	 * set the site id
	 * 
	 * @param siteid
	 */
	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}

	/**
	 * get event id
	 * 
	 * @return
	 */
	public int getEventid() {
		return eventid;
	}

	/**
	 * Get event name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get Description
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get description format (1 = HTML, 0 = MOODLE, 2 = PLAIN or 4 = MARKDOWN)
	 * 
	 * @return
	 */
	public int getFormat() {
		return format;
	}

	/**
	 * get course id
	 * 
	 * @return
	 */
	public int getCourseid() {
		return courseid;
	}

	/**
	 * Get group id
	 * 
	 * @return
	 */
	public int getGroupid() {
		return groupid;
	}

	/**
	 * Get user id
	 * 
	 * @return
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * Get repeat id
	 * 
	 * @return
	 */
	public int getRepeatid() {
		return repeatid;
	}

	/**
	 * Get (Optional) module name
	 * 
	 * @return
	 */
	public String getModulename() {
		return modulename;
	}

	/**
	 * Get instance id
	 * 
	 * @return
	 */
	public int getInstance() {
		return instance;
	}

	/**
	 * Get Event type
	 * 
	 * @return
	 */
	public String getEventtype() {
		return eventtype;
	}

	/**
	 * Get timestart
	 * 
	 * @return
	 */
	public int getTimestart() {
		return timestart;
	}

	/**
	 * Get timeduration
	 * 
	 * @return
	 */
	public int getTimeduration() {
		return timeduration;
	}

	/**
	 * Get visible
	 * 
	 * @return
	 */
	public int getVisible() {
		return visible;
	}

	/**
	 * Get (Optional) unique id of ical events
	 * 
	 * @return
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Get sequence
	 * 
	 * @return
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * Get time modified
	 * 
	 * @return
	 */
	public int getTimemodified() {
		return timemodified;
	}

	/**
	 * Get (Optional) Subscription id
	 * 
	 * @return
	 */
	public int getSubscriptionid() {
		return subscriptionid;
	}


    public int getId()
    {
	return 0;
    }

    public void setId(int id)
    {
	
    }

    public void save()
    {
    }
}
