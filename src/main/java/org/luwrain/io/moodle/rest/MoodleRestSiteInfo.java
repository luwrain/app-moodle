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

import org.luwrain.io.moodle.model.MoodleSiteInfo;

import java.io.Reader;
import java.net.URLEncoder;

import org.luwrain.core.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class MoodleRestSiteInfo
{
    static private final String DEBUG_TAG = "MoodleRestCourses";
    
	private String mUrl;
	private String token;
	MoodleSiteInfo siteInfo = new MoodleSiteInfo();

	public MoodleRestSiteInfo(String mUrl, String token)
    {
	this.mUrl = mUrl;
	this.token = token;
    }

    /**
     * Get the siteinfo for the current account
     * 
     * @return siteinfo object
     * 
     * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
     */
    public MoodleSiteInfo getSiteInfo()
    {
	String format = MoodleRestOption.RESPONSE_FORMAT;
		String function = MoodleRestOption.FUNCTION_GET_SITE_INFO;
		try {
			// Adding all parameters.
			String params = "" + URLEncoder.encode("", "UTF-8");
			// Build a REST call url to make a call.
			String restUrl = mUrl + "/webservice/rest/server.php" + "?wstoken="
					+ token + "&wsfunction=" + function
					+ "&moodlewsrestformat=" + format;
			// Fetch content now
			MoodleRestCall mrc = new MoodleRestCall();
			try (Reader reader = mrc.fetchContent(restUrl, params)) {
			Gson gson = new GsonBuilder().create();
			siteInfo = gson.fromJson(reader, MoodleSiteInfo.class);
			}
		} catch (Exception e) {
			Log.debug(DEBUG_TAG, "URL encoding failed");
			e.printStackTrace();
		}
		if (siteInfo == null)
			return new MoodleSiteInfo();
		return siteInfo;
	}
}
