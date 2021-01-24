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

    /**
     * sort order in the category
     */
    @SerializedName("categorysortorder")
    private int categorySortOrder;

    @SerializedName("fullname")
    private String fullName;

    @SerializedName("idnumber")
    private String idNumber;

    @SerializedName("summary")
    private String summary;

    /**
     * The summary format (1 = HTML, 0 = MOODLE, 2 = PLAIN or 4 = MARKDOWN)
     */
    @SerializedName("summaryformat")
    private int summaryFormat;

    /**
     * The course format: weeks, topics, social, site,..
     */
    @SerializedName("format")
    private String format;

    /**
     * 1 if grades are shown, otherwise 0
     */
    @SerializedName("showgrades")
    private int showGrades;

    /**
     * number of recent items appearing on the course page
     */
	@SerializedName("startdate")
	private int startDate;

    /**
     * (deprecated, use courseformatoptions) number of weeks/topics
     */
    @SerializedName("numsections")
    private int numSections;

    /**
     * largest size of file that can be uploaded into the course
     */
    @SerializedName("maxbytes")
    private int maxBytes;

    /**
     * are Show activity report (yes = 1, no =0)
     */
    @SerializedName("showreports")
    private int showReports;

    /**
     * 1: available to student, 0:not available
     */
    @SerializedName("visible")
    private int visible;

    /**
     * (deprecated, use courseformatoptions) How the hidden sections in the
     * course are displayed to students
     */
    @SerializedName("hiddensections")
    private int hiddenSections;

    /**
     * no group, separate, visible
     */
    @SerializedName("groupmode")
    private int groupMode;

    @SerializedName("groupmodeforce")
    private int groupModeForce;

    @SerializedName("defaultgroupingid")
    private int defaultGroupingId;

    /**
     * timestamp when the course have been created
     */
    @SerializedName("timecreated")
    private int timeCreated;

    /**
     * Timestamp when the course have been modified
     */
    @SerializedName("timemodified")
    private int timeModified;

    /**
     * Enabled, control via completion and activity settings. Disbaled, not
     * shown in activity settings.
     */
    @SerializedName("enablecompletion")
    private int enableCompletion;

    /**
     * 1: yes 0: no
     */
    @SerializedName("completionnotify")
    private int completionNotify;

    /**
     * forced course language
     */
	@SerializedName("lang")
	    private String lang;

	/**
	 * name of the force theme
	 */
	@SerializedName("forcetheme")
	    private String forceTheme;

	/**
	 * additional options for particular course format
	 */
	@SerializedName("courseformatoptions")
	    private List<MoodleCourseFormatOption> courseFormatOptions;

	/**
	 * Exception occurred while retrieving
	 */
	@SerializedName("exception")
	    private String exception;

	/**
	 * Errorcode of error occurred while retrieving
	 */
	@SerializedName("errorcode")
	    private String errorCode;

	/**
	 * Message of error occurred while retrieving
	 */
	@SerializedName("message")
	    private String message;

	/**
	 * Debug info on the error occurred
	 */
	@SerializedName("debuginfo")
	    private String debugInfo;

	private long account;

	/**
	 * Get if this course is enrolled by current user
	 */
	private Boolean isUserCourse = false;

	/**
	 * Get favourite status course in the app
	 */
	private Boolean isFavCourse = false;

	public Course(long account)
	{
	    this.account = account;
	}

	public void  save()
	{
	}
}
