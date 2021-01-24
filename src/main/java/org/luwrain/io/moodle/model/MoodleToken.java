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

public class MoodleToken
{
	String token;

	String error = "";
	String stacktrace;
	String debuginfo;
	String reproductionlink;

	/**
	 * Token value
	 * 
	 * Returns null if there was an error.
	 * 
	 * @return
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Error value
	 * 
	 * Returns null if no errors found
	 * 
	 * @return
	 */
	public String getError() {
		return error;
	}

	/**
	 * Stacktrace value
	 * 
	 * Returns null if no errors found
	 * 
	 * @return
	 */
	public String getStacktrace() {
		return stacktrace;
	}

	/**
	 * Debug info, if enabled by administrator
	 * 
	 * Returns null if not found
	 * 
	 * @return
	 */
	public String getDebuginfo() {
		return debuginfo;
	}

	/**
	 * Reproduction link
	 * 
	 * Returns null if not found
	 * 
	 * @return
	 */
	public String getReproductionlink() {
		return reproductionlink;
	}

	/**
	 * Set error message <br/>
	 * <br/>
	 * Particularly useful for network failure errors
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Appends to the existing error messages<br/>
	 * <br/>
	 * Particularly useful for network failure errors
	 */
	public void appenedError(String error) {
		this.error += error + "\n";
	}
}
