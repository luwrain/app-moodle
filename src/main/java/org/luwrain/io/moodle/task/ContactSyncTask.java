package org.luwrain.io.moodle.task;

import org.luwrain.io.moodle.model.*;
import org.luwrain.io.moodle.model.MDroidNotification;
import org.luwrain.io.moodle.model.MoodleContact;
import org.luwrain.io.moodle.model.MoodleContacts;
import org.luwrain.io.moodle.model.MoodleUser;
import org.luwrain.io.moodle.rest.MoodleRestContact;

import java.util.ArrayList;
import java.util.List;

public class ContactSyncTask {
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
	public ContactSyncTask(String mUrl, String token, long siteid) {
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
	public ContactSyncTask(String mUrl, String token, long siteid,
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
	 * Sync all the user contacts in the current site.
	 * 
	 * @return syncStatus
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public Boolean syncAllContacts() {
		MoodleRestContact mrc = new MoodleRestContact(mUrl, token);
		MoodleContacts mContacts = mrc.getAllContacts();

		/** Error checking **/
		// Some network or encoding issue.
		if (mContacts == null) {
			error = "Network issue!";
			return false;
		}

		// Moodle exception
		if (mContacts.getErrorcode() != null) {
			error = mContacts.getErrorcode();
			// No additional debug info as that needs context
			return false;
		}

		// Save all contacts
		ArrayList<MoodleContact> contacts;

		// Online contacts
		contacts = mContacts.getOnline();
		saveToDb(contacts, MoodleContact.STATUS_ONLINE);

		// Offline contacts
		contacts = mContacts.getOffline();
		saveToDb(contacts, MoodleContact.STATUS_OFFLINE);

		// Strangers contacts
		contacts = mContacts.getStrangers();
		saveToDb(contacts, MoodleContact.STATUS_STRANGER);

		return true;
	}

	/**
	 * Adds user as a contact
	 * 
	 * @param user
	 *            MoodleUser
	 * @return True if success
	 */
	public Boolean AddContact(MoodleUser user) {
		MoodleRestContact mrc = new MoodleRestContact(mUrl, token);
		if (mrc.addContact(user))
			return true;
		error = mrc.getError();
		return false;
	}

	/**
	 * Remove user as a contact
	 * 
	 * @param user
	 *            MoodleUser
	 * @return True if success
	 */
	public Boolean RemoveContact(MoodleUser user) {
		MoodleRestContact mrc = new MoodleRestContact(mUrl, token);
		if (mrc.removeContact(user))
			return true;
		error = mrc.getError();
		return false;
	}

	/**
	 * Returns error occured during the last request
	 * 
	 * @return
	 */
	public String getError() {
		return error;
	}

	private void saveToDb(ArrayList<MoodleContact> contacts, int status) {
		List<MoodleContact> dbContacts;
		MoodleContact contact = new MoodleContact();

		if (contacts != null)
			for (int i = 0; i < contacts.size(); i++) {
				contact = contacts.get(i);
				contact.setStatus(status);
				contact.setSiteid(siteid);

				dbContacts = DB.find(MoodleContact.class,
						"contactid = ? and siteid = ?", contacts.get(i)
								.getContactid() + "", siteid + "");
				if (!dbContacts.isEmpty())
					contact.setId(dbContacts.get(0).getId());

				// set notifications if enabled
				else if (notification) {
					new MDroidNotification(siteid,
							MDroidNotification.TYPE_CONTACT,
							"New contacts found", contact.getFullname()
									+ " is now in your contacts").save();
					notificationcount++;
				}
				contact.save();
			}
	}
}
