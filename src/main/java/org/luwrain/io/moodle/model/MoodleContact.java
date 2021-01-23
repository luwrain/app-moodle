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
//import com.orm.dsl.Ignore;

public class MoodleContact //extends SugarRecord<MoodleContact>
{

	// Constants
	//@Ignore
	public static final int STATUS_ONLINE = 0;
	////@Ignore
	public static final int STATUS_OFFLINE = 1;
	////@Ignore
	public static final int STATUS_STRANGER = 2;

	// since id is a reserved field in SugarRecord
	@SerializedName("id")
	int contactid;

	@SerializedName("fullname")
	String fullname;

	@SerializedName("profileimageurl")
	String profileimageurl;

	@SerializedName("profileimageurlsmall")
	String profileimageurlsmall;

	@SerializedName("unread")
	int unread = 0;

	@SerializedName("status")
	int status;

	// Error fields are required only for Contacts instead

	// Relational fields
	long siteid;

	public MoodleContact() {

	}

	/**
	 * @param contactid
	 *            Moodle userid
	 * @param fullname
	 *            Full name of the user
	 */
	public MoodleContact(int contactid, String fullname) {
		this.contactid = contactid;
		this.fullname = fullname;
	}

	/**
	 * Moodle contact id for the contact
	 * 
	 * @return
	 */
	public int getContactid() {
		return contactid;
	}

	/**
	 * Contact fullname
	 * 
	 * @return
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * Contact profile picture url
	 * 
	 * @return
	 */
	public String getProfileimageurl() {
		return profileimageurl;
	}

	/**
	 * Smaller picture of user
	 * 
	 * @return
	 */
	public String getProfileimageurlsmall() {
		return profileimageurlsmall;
	}

	/**
	 * Unread message count
	 * 
	 * @return
	 */
	public int getUnread() {
		return unread;
	}

	/**
	 * Status of the user. There are 3 possibilities, <br/>
	 * <ul>
	 * <li>STATUS_ONLINE</li>
	 * <li>STATUS_OFFLINE</li>
	 * <li>STATUS_STRANGER</li>
	 * <ul>
	 * 
	 * @return status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Get the siteid of this course
	 * 
	 * @return
	 */
	public long getSiteid() {
		return siteid;
	}

	/**
	 * set the site id of this course
	 * 
	 * @param siteid
	 */
	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}

	/**
	 * Set the status of the user. There are 3 possibilities, <br/>
	 * <ul>
	 * <li>STATUS_ONLINE</li>
	 * <li>STATUS_OFFLINE</li>
	 * <li>STATUS_STRANGER</li>
	 * <ul>
	 * 
	 * @return status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	public class MoodleContactWarnings {
		@SerializedName("item")
		String item;

		@SerializedName("itemid")
		int itemid;

		@SerializedName("warningcode")
		String warningcode;// the warning code can be used by the client app to
							// implement specific behaviour

		@SerializedName("message")
		String message;// untranslated english message to explain the warning
	}

    
    public long getId()
    {
	return 0;
    }

    public void setId(long id)
    {
    }

    public void save()
    {
    }

}
