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

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

/**
 * Moodle returns contacts in 3 objects - Online, Offline and Strangers with
 * contacts filled in each section. We won't be using this model for db
 * operations but instead only easy contact retrieval from json. Status of the
 * contact will be saved in the db using additional field instead.
 * 
 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
 * 
 */
public class MoodleContacts {
	@SerializedName("online")
	ArrayList<MoodleContact> online;

	@SerializedName("offline")
	ArrayList<MoodleContact> offline;

	@SerializedName("strangers")
	ArrayList<MoodleContact> strangers;

	// Errors. Not to be stored in sql db.
	@SerializedName("exception")
	String exception;

	@SerializedName("errorcode")
	String errorcode;

	@SerializedName("message")
	String message;

	@SerializedName("debuginfo")
	String debuginfo;

	/**
	 * Get ArrayList of online MoodleContact
	 * 
	 * @return online contacts
	 */
	public ArrayList<MoodleContact> getOnline() {
		return online;
	}

	/**
	 * Get ArrayList of offline MoodleContact
	 * 
	 * @return offline contacts
	 */
	public ArrayList<MoodleContact> getOffline() {
		return offline;
	}

	/**
	 * Get ArrayList of stranger MoodleContact
	 * 
	 * @return stranger contacts
	 */
	public ArrayList<MoodleContact> getStrangers() {
		return strangers;
	}

	/**
	 * Exception occurred while retrieving
	 * 
	 * @return
	 */
	public String getException() {
		return exception;
	}

	/**
	 * Errorcode of error occurred while retrieving
	 * 
	 * @return
	 */
	public String getErrorcode() {
		return errorcode;
	}

	/**
	 * Message of error occurred while retrieving
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Debug info on the error occurred
	 * 
	 * @return
	 */
	public String getDebuginfo() {
		return debuginfo;
	}

}
