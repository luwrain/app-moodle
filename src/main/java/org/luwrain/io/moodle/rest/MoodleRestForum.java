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

import org.luwrain.io.moodle.model.MoodleForum;

import java.io.Reader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.luwrain.core.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MoodleRestForum {
	private final String DEBUG_TAG = "MoodleRestForum";
	private String mUrl;
	private String token;

	public MoodleRestForum(String mUrl, String token) {
		this.mUrl = mUrl;
		this.token = token;
	}

	/**
	 * Get all the forums of given course in the Moodle site.<br/>
	 * 
	 * @return ArrayList of MoodleForums
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public ArrayList<MoodleForum> getForums(String courseid) {
		ArrayList<String> courseids = new ArrayList<>();
		courseids.add(courseid);
		return getForums(courseids);
	}

	/**
	 * Get all the forums of given courses in the Moodle site.<br/>
	 * 
	 * @return ArrayList of MoodleForums
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public ArrayList<MoodleForum> getForums(ArrayList<String> courseids) {
		ArrayList<MoodleForum> mForums = new ArrayList<>();
		String format = MoodleRestOption.RESPONSE_FORMAT;
		String function = MoodleRestOption.FUNCTION_GET_FORUMS;

		try {
			// Adding all parameters.
			String params = "";
			for (int i = 0; i < courseids.size(); i++) {
				params += "&courseids[" + i + "]="
						+ URLEncoder.encode(courseids.get(i), "UTF-8");
			}

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
			mForums = gson.fromJson(reader, new TypeToken<List<MoodleForum>>() {
			}.getType());
			reader.close();

		} catch (Exception e) {
			Log.debug(DEBUG_TAG, "URL encoding failed");
			e.printStackTrace();
		}

		return mForums;
	}
}
