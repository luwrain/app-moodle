package org.luwrain.io.moodle.model;

import java.util.*;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@NoArgsConstructor
public class Course
{
    private long id;

	@SerializedName("id")
	private int courseId;

	@SerializedName("shortname")
	private String shortname;

	@SerializedName("categoryid")
	private int categoryId;

	@SerializedName("categorysortorder")
	private int categorySortOrder;

	@SerializedName("fullname")
	private String fullName;

	@SerializedName("idnumber")
	private String idNumber;

	@SerializedName("summary")
	private String summary;

	@SerializedName("summaryformat")
	private int summaryFormat;

	@SerializedName("format")
	private String format;

	@SerializedName("showgrades")
	private int showGrades;

	@SerializedName("newsitems")
	private int newsItems;

	@SerializedName("startdate")
	private int startDate;

	@SerializedName("numsections")
	private int numSections;

	@SerializedName("maxbytes")
	private int maxBytes;

	@SerializedName("showreports")
	private int showReports;

	@SerializedName("visible")
	private int visible;

	@SerializedName("hiddensections")
	private int hiddenSections;

	@SerializedName("groupmode")
private int groupMode;

	@SerializedName("groupmodeforce")
	private int groupModeForce;

	@SerializedName("defaultgroupingid")
	private int defaultGroupingId;

	@SerializedName("timecreated")
	private int timeCreated;

	@SerializedName("timemodified")
	private int timeModified;

	@SerializedName("enablecompletion")
	private int enableCompletion;

	@SerializedName("completionnotify")
	private int completionNotify;

	@SerializedName("lang")
	private String lang;

	@SerializedName("forcetheme")
	private String forceTheme;

	//@Ignore
	@SerializedName("courseformatoptions")
	private List<MoodleCourseFormatOption> courseFormatOptions;

	// Errors. Not to be stored in sql db.
	//@Ignore
	@SerializedName("exception")
	private String exception;

	//@Ignore
	@SerializedName("errorcode")
	private String errorCode;

	//@Ignore
	@SerializedName("message")
	private String message;

	//@Ignore
	@SerializedName("debuginfo")
	private String debugInfo;

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
