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

public class MoodleException {
	@SerializedName("exception")
	String exception;

	@SerializedName("errorcode")
	String errorcode;

	@SerializedName("message")
	String message;

	@Override
	public String toString() {
		return "exception: " + exception + "\n\n errorcode: " + errorcode
				+ "\n\n message: " + message;
	}

	public String getException() {
		return exception;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public String getMessage() {
		return message;
	}
}
