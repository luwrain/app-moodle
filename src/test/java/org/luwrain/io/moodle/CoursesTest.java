
package org.luwrain.io.moodle;

import java.io.*;
import java.util.*;

import org.junit.*;

import org.luwrain.core.*;
import org.luwrain.io.moodle.model.*;
import org.luwrain.io.moodle.moodlerest.*;

public class CoursesTest extends Assert
{
    @Test public void list() throws IOException
    {
	final Properties props = new Properties();
	try (final InputStream is = CoursesTest.class.getResourceAsStream("service.properties")) {
	    if (is == null)
		return;
	    props.load(new InputStreamReader(is, "UTF-8"));
	}
	final String url = props.getProperty("url");
	final String token = props.getProperty("token");
	assertNotNull(url);
	assertNotNull(token);
	assertFalse(url.trim().isEmpty());
	assertFalse(token.trim().isEmpty());
	final MoodleRestCourse c = new MoodleRestCourse(url, token);
	final List<MoodleCourse> res = c.getAllCourses();
    }
}
