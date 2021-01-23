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

import com.google.gson.annotations.SerializedName;
import lombok.*;
//import com.orm.SugarRecord;

/**
 * Represents course of any moodle user. Used in User profiles listing. A better
 * way could be retreiving from logged in user account using courseid but the
 * course may or may not be in his profile.
 * 
 * @author Praveen Kumar Pendyala<praveen@praveenkumar.co.in>
 * 
 */
@Data
public class MoodleUserCourse //extends SugarRecord<MoodleUserCourse>
{
    int id;
    
	@SerializedName("id")
	int courseid;

	@SerializedName("fullname")
	String fullname;

	@SerializedName("shortname")
	String shortname;

	// Relational fields - for less logout complexity
	long siteid;
	int userid; // Moodle userid

	/**
	 * Get id of the course
	 * 
	 * @return
	 */
	public int getCourseid() {
		return courseid;
	}

	/**
	 * Get course fullname
	 * 
	 * @return
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * Get course shortname
	 * 
	 * @return
	 */
	public String getShortname() {
		return shortname;
	}

	/**
	 * Get siteid of this record
	 * 
	 * @return
	 */
	public long getSiteid() {
		return siteid;
	}

	/**
	 * Userid of user to whom this course belongs to
	 * 
	 * @return
	 */
	public long getUserid() {
		return userid;
	}

	/**
	 * Set siteid of this record
	 * 
	 * @return
	 */
	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}

	/**
	 * Userid of user to whom this course belongs to
	 * 
	 * @return
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

    public void save()
    {
    }
}
