package org.luwrain.io.moodle.task;

import java.io.*;

import java.util.*;

import org.luwrain.io.moodle.model.*;
import org.luwrain.io.moodle.moodlerest.*;

/**
 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
 */
public final class SyncCourses
{
    private final String mUrl;
    private final String token;
    private final long siteid;
    private String error = null;

    public SyncCourses(String mUrl, String token, long siteid)
    {
	this.mUrl = mUrl;
	this.token = token;
	this.siteid = siteid;
    }

    /**
     * Sync all the courses in the current site.
     * 
     * @return syncStatus
     */
    public Boolean syncAllCourses() throws IOException
    {
	final MoodleRestCourse mrc = new MoodleRestCourse(mUrl, token);
	final List<Course> mCourses = mrc.getAllCourses();
	/** Error checking **/
	// Some network or encoding issue.
	if (mCourses.isEmpty())
	{
	    error = "Network issue!";
	    return false;
	}
	// Moodle exception
	if (mCourses.size() == 1 && mCourses.get(0).getCourseId() == 0)
	{
	    error = "Moodle Exception: User don't have permissions!";
	    return false;
	}
	// Add siteid to all courses and update
		List<Course> dbCourses = null;
		for (int i = 0; i < mCourses.size(); i++)
		{
			final Course course = mCourses.get(i);
			course.setAccount(siteid);
			// Update or save in database
			dbCourses = DB.find(Course.class,
					"courseid = ? and siteid = ?", course.getCourseId() + "",
					course.getAccount() + "");
			if (dbCourses != null && !dbCourses.isEmpty())
			{
				// Set app specific fields explicitly
				course.setId(dbCourses.get(0).getId());
				course.setIsUserCourse(dbCourses.get(0).getIsUserCourse());
				course.setIsFavCourse(dbCourses.get(0).getIsFavCourse());
			}
			course.save();
		}
		return true;
	}

	/**
	 * Sync all courses of logged in user in the current site.
	 * 
	 * @return syncStatus
	 */
	public Boolean syncUserCourses()
    {
		// Get userid
		MoodleSiteInfo site = DB.findById(MoodleSiteInfo.class,
				siteid);

		if (site == null)
			return false;
		int userid = site.getUserid();
		MoodleRestCourse mrc = new MoodleRestCourse(mUrl, token);
		ArrayList<Course> mCourses = mrc.getEnrolledCourses(String.valueOf(userid));
		/** Error checking **/
		// Some network or encoding issue.
		if (mCourses == null)
			return false;
		// Some network or encoding issue.
		if (mCourses.isEmpty())
			return false;
		// Moodle exception
		if (mCourses.size() == 1 && mCourses.get(0).getCourseId() == 0)
			return false;
		// Add siteid and isUserCourse to all courses and update
		Course course = new Course();
		List<Course> dbCourses;
		for (int i = 0; i < mCourses.size(); i++)
		{
			course = mCourses.get(i);
			course.setAccount(siteid);
			course.setIsUserCourse(true);
			// Update or save in database
			dbCourses = DB.find(Course.class,
					"courseid = ? and siteid = ?", course.getCourseId() + "",
					course.getAccount() + "");
			if (!dbCourses.isEmpty()) {
				// Set app specific fields explicitly
				course.setId(dbCourses.get(0).getId());
				course.setIsFavCourse(dbCourses.get(0).getIsFavCourse());
			}
			course.save();
		}
		return true;
	}

	/**
	 * The error message from the last failed sync operation
	 * 
	 * @return The error message from the last failed sync operation
	 */
	public String getError()
    {
		return error;
	}
}
