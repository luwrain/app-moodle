
package org.luwrain.io.moodle;

import java.io.*;
import java.util.*;

import org.junit.*;

class Base extends Assert
{
    protected String url = null;
    protected String user = null;
    protected String passwd = null;
    protected String token = null;

    protected void loadProps() throws IOException
    {
	this.url = null;
	this.user = null;
	this.passwd = null;
	this.token = null;
		final Properties props = new Properties();
	try (final InputStream is = Base.class.getResourceAsStream("service.properties")) {
	    if (is == null)
		return;
	    props.load(new InputStreamReader(is, "UTF-8"));
	}
	this.url = props.getProperty("url");
	this.user = props.getProperty("user");
	this.passwd = props.getProperty("passwd");
	this.token = props.getProperty("token");
    }

    protected boolean isReadyPasswd()
    {
	return url != null && !url.isEmpty() &&
	user != null && !user.isEmpty() &&
	passwd != null && !passwd.isEmpty();
    }
}
