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

package org.luwrain.io.moodle.rest;

import org.luwrain.io.moodle.model.MoodleSection;

import java.io.Reader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.luwrain.core.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MoodleRestCourseContent
{
	private final String DEBUG_TAG = "MoodleRestCourseContents";
    
	private final String mUrl;
	private final String token;

	public MoodleRestCourseContent(String mUrl, String token) {
		this.mUrl = mUrl;
		this.token = token;
	}

	/**
	 * Get all the sections in the Course.<br/>
	 * User may not have permission to do this. In such cases, only one course
	 * entry is added to the list with only error fields filled. If no entries
	 * are found, then it could mean a network or encoding issue.
	 * 
	 * @return ArrayList<MoodleCourse>
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public ArrayList<MoodleSection> getCourseContent(String courseid)
    {
		ArrayList<MoodleSection> sections = null;
		String format = MoodleRestOption.RESPONSE_FORMAT;
		String function = MoodleRestOption.FUNCTION_GET_COURSE_CONTENTS;

		try {
			// Adding all parameters.
			String params = "&courseid=" + URLEncoder.encode(courseid, "UTF-8");

			// Build a REST call url to make a call.
			String restUrl = mUrl + "/webservice/rest/server.php" + "?wstoken="
					+ token + "&wsfunction=" + function
					+ "&moodlewsrestformat=" + format;

			// Fetch content now.
			MoodleRestCall mrc = new MoodleRestCall();
			Reader reader = mrc.fetchContent(restUrl, params);
			GsonExclude ex = new GsonExclude();
			Gson gson = new GsonBuilder()
					.addDeserializationExclusionStrategy(ex)
					.addSerializationExclusionStrategy(ex).create();
			sections = gson.fromJson(reader,
					new TypeToken<List<MoodleSection>>() {
					}.getType());
			reader.close();

		} catch (Exception e) {
			Log.debug(DEBUG_TAG, "URL encoding failed");
			e.printStackTrace();
		}

		return sections;
	}

}
