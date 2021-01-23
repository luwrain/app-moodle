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
 * Moodle returns events in 2 objects - Events list and list of warnings We
 * won't be using this model for db operations but instead only easy event
 * retrieval from json.
 * 
 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
 * 
 */
public class MoodleEvents {

	@SerializedName("events")
	ArrayList<MoodleEvent> events;

	@SerializedName("warnings")
	ArrayList<MoodleEventWarning> warnings;

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
	 * Get ArrayList of Events
	 * 
	 * @return events
	 */
	public ArrayList<MoodleEvent> getEvents() {
		return events;
	}

	/**
	 * Get ArrayList of Event warnings
	 * 
	 * @return warnings
	 */
	public ArrayList<MoodleEventWarning> getWarnings() {
		return warnings;
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

	/**
	 * Represents warnings that come along with a Events call. <br/>
	 * <br/>
	 * This is not so important to be implemented as main model I guess
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public class MoodleEventWarning {
		String item;
		int itemid;
		String warningcode;
		String message;

		/**
		 * Get item
		 * 
		 * @return
		 */
		public String getItem() {
			return item;
		}

		/**
		 * Get item id
		 * 
		 * @return
		 */
		public int getItemid() {
			return itemid;
		}

		/**
		 * Get the warning code can be used by the client app to implement
		 * specific behaviour
		 * 
		 * @return
		 */
		public String getWarningcode() {
			return warningcode;
		}

		/**
		 * Get untranslated english message to explain the warning
		 * 
		 * @return
		 */
		public String getMessage() {
			return message;
		}
	}
}
