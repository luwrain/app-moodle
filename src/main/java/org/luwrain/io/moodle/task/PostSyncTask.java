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

package org.luwrain.io.moodle.task;

import org.luwrain.io.moodle.model.*;
import org.luwrain.io.moodle.model.MDroidNotification;
import org.luwrain.io.moodle.model.MoodleDiscussion;
import org.luwrain.io.moodle.model.MoodlePost;
import org.luwrain.io.moodle.model.MoodlePosts;
import org.luwrain.io.moodle.rest.MoodleRestPost;

import java.util.ArrayList;
import java.util.List;

public class PostSyncTask {
	String mUrl;
	String token;
	long siteid;

	String error;
	Boolean notification;
	int notificationcount;

	/**
	 * 
	 * @param mUrl
	 * @param token
	 * @param siteid
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public PostSyncTask(String mUrl, String token, long siteid) {
		this.mUrl = mUrl;
		this.token = token;
		this.siteid = siteid;
		this.notification = false;
		this.notificationcount = 0;
	}

	/**
	 * 
	 * @param mUrl
	 * @param token
	 * @param siteid
	 * @param notification
	 *            If true, sets notifications for new contents
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public PostSyncTask(String mUrl, String token, long siteid,
			Boolean notification) {
		this.mUrl = mUrl;
		this.token = token;
		this.siteid = siteid;
		this.notification = notification;
		this.notificationcount = 0;
	}

	/**
	 * Get the notifications count. Notifications should be enabled during
	 * Object instantiation.
	 * 
	 * @return notificationcount
	 */
	public int getNotificationcount() {
		return notificationcount;
	}

	/**
	 * Sync all topics in a discussion.
	 * 
	 * @return syncStatus
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public Boolean syncPosts(int discussionid) {
		MoodleRestPost mrp = new MoodleRestPost(mUrl, token);
		MoodlePosts moodlePosts = mrp.getPosts(discussionid);

		/** Error checking **/
		// Some network or encoding issue.
		if (moodlePosts == null) {
			error = "Network issue!";
			return false;
		}

		// Moodle exception
		if (moodlePosts.getErrorcode() != null) {
			error = moodlePosts.getErrorcode();
			// No additional debug info as that needs context
			return false;
		}

		ArrayList<MoodlePost> mPosts = moodlePosts.getPosts();
		// Warnings are not being handled
		List<MoodlePost> dbPosts;
		MoodlePost post = new MoodlePost();

		if (mPosts != null)
			for (int i = 0; i < mPosts.size(); i++) {
				post = mPosts.get(i);
				post.setSiteid(siteid);

				dbPosts = DB.find(MoodlePost.class,
						"postid = ? and siteid = ?", String.valueOf(post.getPostid()),
						String.valueOf(siteid));
				if (!dbPosts.isEmpty())
					post.setId(dbPosts.get(0).getId());

				// set notifications if enabled
				else if (notification) {
					List<MoodleDiscussion> dbDiscussions = DB.find(MoodleDiscussion.class,
									"discussionid = ? and siteid = ?", String.valueOf(siteid)
											, String.valueOf(discussionid));
					MoodleDiscussion discussion = (dbDiscussions != null && !dbDiscussions.isEmpty()) ? dbDiscussions.get(0) : null;

					if (discussion != null) {
						new MDroidNotification(siteid,
								MDroidNotification.TYPE_FORUM_REPLY,
								"New forum reply from "
										+ post.getUserfullname(),
								"New reply in " + discussion.getName(), 1,
								post.getDiscussionid()).save();
						notificationcount++;
					}
				}
				post.save();
			}

		return true;
	}

	/**
	 * Sync all topics in the list of discussions.
	 * 
	 * Note: Moodle doesn't support fetching of posts from more than one
	 * discussion at a time so, this is realized using multiple calls - one per
	 * discussionid.
	 * 
	 * @return syncStatus
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public Boolean syncPosts(ArrayList<Integer> discussionids) {
		Boolean status = true;

		if (discussionids == null || discussionids.isEmpty())
			return false;

		for (int i = 0; i < discussionids.size(); i++)
			status = status & syncPosts(discussionids.get(i));

		return status;
	}
}
