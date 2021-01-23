package org.luwrain.io.moodle.model;

import java.util.*;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@NoArgsConstructor
public class Course
{

    long id;
	@SerializedName("id")
	int courseid;

	@SerializedName("shortname")
	String shortname;

	@SerializedName("categoryid")
	int categoryId;

	@SerializedName("categorysortorder")
	int categorySortOrder;

	@SerializedName("fullname")
	String fullName;

	@SerializedName("idnumber")
	String idNumber;

	@SerializedName("summary")
	String summary;

	@SerializedName("summaryformat")
	int summaryFormat;

	@SerializedName("format")
	String format;

	@SerializedName("showgrades")
	int showGrades;

	@SerializedName("newsitems")
	int newsItems;

	@SerializedName("startdate")
	int startDate;

	@SerializedName("numsections")
	int numSections;

	@SerializedName("maxbytes")
	int maxBytes;

	@SerializedName("showreports")
	int showReports;

	@SerializedName("visible")
	int visible;

	@SerializedName("hiddensections")
	int hiddenSections;

	@SerializedName("groupmode")
	int groupMode;

	@SerializedName("groupmodeforce")
	int groupModeForce;

	@SerializedName("defaultgroupingid")
	int defaultGroupingId;

	@SerializedName("timecreated")
	int timeCreated;

	@SerializedName("timemodified")
	int timeModified;

	@SerializedName("enablecompletion")
	int enableCompletion;

	@SerializedName("completionnotify")
	int completionNotify;

	@SerializedName("lang")
	String lang;

	@SerializedName("forcetheme")
	String forceTheme;

	//@Ignore
	@SerializedName("courseformatoptions")
	List<MoodleCourseFormatOption> courseFormatOptions;

	// Errors. Not to be stored in sql db.
	//@Ignore
	@SerializedName("exception")
	String exception;

	//@Ignore
	@SerializedName("errorcode")
	String errorCode;

	//@Ignore
	@SerializedName("message")
	String message;

	//@Ignore
	@SerializedName("debuginfo")
	String debugInfo;

	// Relational fields
	long account;
	Boolean isUserCourse = false;
	Boolean isFavCourse = false;

	public Course(long account)
    {
	this.account = account;
	}


    public void  save()
    {
    }

}
