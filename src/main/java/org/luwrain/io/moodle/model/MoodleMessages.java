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

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Moodle returns messages in 2 objects - Messages list and list of warnings. We
 * won't be using this model for db operations but instead only easy message
 * retrieval from json.
 * 
 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
 * 
 */
public class MoodleMessages {

	@SerializedName("messages")
	List<MoodleMessage> messages;

	@SerializedName("warnings")
	List<MoodleMessageWarning> warnings;

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
	 * Get List of Messages
	 * 
	 * @return messages
	 */
	public List<MoodleMessage> getMessages() {
		return messages;
	}

	/**
	 * Get List of Message warnings
	 * 
	 * @return warnings
	 */
	public List<MoodleMessageWarning> getWarnings() {
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
	 * Represents warnings that come along with a Messages call. <br/>
	 * <br/>
	 * This is not so important to be implemented as main model I guess
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public class MoodleMessageWarning {
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
