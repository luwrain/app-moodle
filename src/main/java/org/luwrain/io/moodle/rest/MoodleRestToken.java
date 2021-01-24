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

import org.luwrain.io.moodle.model.MoodleToken;

import java.io.*;
import java.net.*;

import com.google.gson.*;

import org.luwrain.core.*;


/**
 * 	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
 */
public final class MoodleRestToken
{
	static private final String LOG_COMPONENT = "MoodleToken";

	private final String uname;
	private final String pswd;
	private final String url;
	private MoodleToken token = new MoodleToken();

    public MoodleRestToken(String uname, String pswd, String baseUrl)
    {
	this.uname = uname;
	this.pswd = pswd;
	this.url = baseUrl + "/login/token.php";
    }

	/**
	 * Tries 3 different web service and returns a token for the given username
	 * and password combination <br/>
	 * <br/>
	 * If there were errors, they can be checked in the error field of the token
	 * object
	 * 
	 * @return MoodleToken object
	 */
	public MoodleToken getToken()
    {
		String urlParams = "";

		// set required parameters for token url
		try {
			urlParams = "username=" + URLEncoder.encode(uname, "UTF-8")
					+ "&password=" + URLEncoder.encode(pswd, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.debug(LOG_COMPONENT, "credential encoding failed!");
			e.printStackTrace();
		}

		// Check MDroid service.
		getTokenForService(urlParams, MoodleRestOption.SERVICE_MDROID);

		// Check Moody service if above failed.
		if (token != null) {
			if (token.getToken() == null)
				getTokenForService(urlParams, MoodleRestOption.SERVICE_MOODY);
		} else {
			token = new MoodleToken();
			token.appenedError("Token fetch failed!");
		}

		// Check Moodle mobile service if above failed.
		if (token != null) {
			if (token.getToken() == null)
				getTokenForService(urlParams,
						MoodleRestOption.SERVICE_MOODLE_MOBILE);
		} else
		{
			token = new MoodleToken();
			token.appenedError("Token fetch failed!");
		}

		return token;
	}

	private void getTokenForService(String urlParams, String serviceName)
    {
		final HttpURLConnection con;
		try {
			con = (HttpURLConnection) new URL(url + "?" + urlParams
					+ "&service=" + serviceName).openConnection();
			con.setRequestProperty("Accept", "application/xml");
			con.setRequestProperty("Content-Language", "en-US");
			con.setDoOutput(true);

			OutputStreamWriter writer = new OutputStreamWriter(
					con.getOutputStream());
			writer.write("");
			writer.flush();
			writer.close();

			// Get Response
			Reader reader = new InputStreamReader(con.getInputStream());

			Gson gson = new GsonBuilder().create();
			token = gson.fromJson(reader, MoodleToken.class);
			reader.close();

		}
		catch (IOException e)
		{
			token.appenedError("\n" + serviceName + " : " + e.getMessage());
		}

	}

	/**
	 * Get token for a custom service name
	 * 
	 * @param serviceName
	 * @return token object
	 */
	public MoodleToken getCustomServiceToken(String serviceName)
    {
		String urlParams = "";

		// set required parameters for token url
		try {
			urlParams = "username=" + URLEncoder.encode(uname, "UTF-8")
					+ "&password=" + URLEncoder.encode(pswd, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.debug(LOG_COMPONENT, "credential encoding failed!");
			e.printStackTrace();
		}

		// Request for custom service token.
		getTokenForService(urlParams, serviceName);

		return token;
	}
}
