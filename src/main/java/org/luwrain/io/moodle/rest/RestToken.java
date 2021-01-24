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

import java.io.*;
import java.net.*;

import com.google.gson.*;

import org.luwrain.io.moodle.model.*;

import org.luwrain.core.*;

/**
 * 	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
 */
public final class RestToken
{
    static public final String
	MDROID = MoodleRestOption.SERVICE_MDROID,
	MOODY = MoodleRestOption.SERVICE_MOODY,
	MOBILE = MoodleRestOption.SERVICE_MOODLE_MOBILE;

    private final String user;
    private final String passwd;
    private final String url;

    public RestToken(String baseUrl, String user, String passwd)
    {
	this.url = baseUrl + "/login/token.php";
	this.user = user;
	this.passwd = passwd;
    }

    public Token getToken(String service) throws IOException
    {
	final StringBuilder urlParams = new StringBuilder();
	urlParams.append("username=").append(URLEncoder.encode(user, "UTF-8"))
	.append("&password=").append(URLEncoder.encode(passwd, "UTF-8"));
	return getTokenForService(new String(urlParams), service);
    }

    private Token getTokenForService(String urlParams, String serviceName) throws IOException
    {
	final Gson gson = new GsonBuilder().create();
	final HttpURLConnection con;
	con = (HttpURLConnection) new URL(url + "?" + urlParams + "&service=" + serviceName).openConnection();
	con.setRequestProperty("Accept", "application/xml");
	con.setRequestProperty("Content-Language", "en-US");
	con.setDoOutput(true);
	try (final OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream())) {
	    writer.write("");
	    writer.flush();
	}
	// Get Response
	try (final Reader reader = new InputStreamReader(con.getInputStream())) {
	    return gson.fromJson(reader, Token.class);
	}
    }
}
