
package org.luwrain.io.moodle.moodlerest;

import java.util.*;
import java.io.*;
import java.net.*;

import com.google.gson.*;
import com.google.gson.reflect.*;

import org.luwrain.core.*;

import org.luwrain.io.moodle.helper.GsonExclude;
import org.luwrain.io.moodle.model.*;


/**
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
public final class MoodleRestCourse
{
	static private final String LOG_COMPONENT = "MoodleRestCourses";
	private final String mUrl;
	private final String token;

	public MoodleRestCourse(String mUrl, String token)
    {
		this.mUrl = mUrl;
		this.token = token;
	}

    /**
     * Get all the courses in the Moodle site.<br/>
     * User may not have permission to do this. In such cases, only one course
     * entry is added to the list with only error fields filled. If no entries
     * are found, then it could mean a network or encoding issue.
     * 
     * @return ArrayList of MoodleCourse
     * 
     */
    public List<Course> getAllCourses() throws IOException
    {
	final String format = MoodleRestOption.RESPONSE_FORMAT;
	final String function = MoodleRestOption.FUNCTION_GET_ALL_COURSES;
	// Adding all parameters.
	String params = "" + URLEncoder.encode("", "UTF-8");
	// Build a REST call url to make a call.
	String restUrl = mUrl + "/webservice/rest/server.php" + "?wstoken="
	+ token + "&wsfunction=" + function
	+ "&moodlewsrestformat=" + format;
	// Fetch content now.
	final MoodleRestCall mrc = new MoodleRestCall();
	try (final Reader reader = mrc.fetchContent(restUrl, params)) {
	    final GsonExclude ex = new GsonExclude();
	    final Gson gson = new GsonBuilder()
	    .addDeserializationExclusionStrategy(ex)
	    .addSerializationExclusionStrategy(ex).create();
	    return gson.fromJson(reader,
				 new TypeToken<List<Course>>() {
				 }.getType());
	}
    }

	/**
	 * Get all the courses that a user is enrolled in<br/>
	 * In case of errors, only one course entry is added to the list with only
	 * error fields filled. If no entries are found, then it could mean a
	 * network or encoding issue.
	 * 
	 * 
	 * @param userId
	 *            userId of the user whose courses are needed
	 * @return ArrayList<MoodleCourse>
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public ArrayList<Course> getEnrolledCourses(String userId) {
		ArrayList<Course> mCourses = new ArrayList<>();
		String format = MoodleRestOption.RESPONSE_FORMAT;
		String function = MoodleRestOption.FUNCTION_GET_ENROLLED_COURSES;

		try {
			// Adding all parameters.
			String params = "&" + URLEncoder.encode("userid", "UTF-8") + "="
					+ userId;

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
			mCourses = gson.fromJson(reader,
					new TypeToken<List<Course>>() {
					}.getType());
			reader.close();

		} catch (Exception e) {
			Log.debug(LOG_COMPONENT, "URL encoding failed");
			e.printStackTrace();
		}

		return mCourses;
	}

}
