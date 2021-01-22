package in.co.praveenkumar.mdroid.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Moodle returns messages in 2 objects - Messages list and list of warnings. We
 * won't be using this model for db operations but instead only easy message
 * retrieval from json.
 * 
 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
 * 
 */
public class MoodleMessages {

	@SerializedName("messages")
	List<MoodleMessage> messages;

	@SerializedName("warnings")
	List<MoodleMessageWarning> warnings;

	// Errors. Not to be stored in sql db.
	@SerializedName("exception")
	String exception;

	@SerializedName("errorcode")
	String errorcode;

	@SerializedName("message")
	String message;

	@SerializedName("debuginfo")
	String debuginfo;

	/**
	 * Get List of Messages
	 * 
	 * @return messages
	 */
	public List<MoodleMessage> getMessages() {
		return messages;
	}

	/**
	 * Get List of Message warnings
	 * 
	 * @return warnings
	 */
	public List<MoodleMessageWarning> getWarnings() {
		return warnings;
	}

	/**
	 * Exception occurred while retrieving
	 * 
	 * @return
	 */
	public String getException() {
		return exception;
	}

	/**
	 * Errorcode of error occurred while retrieving
	 * 
	 * @return
	 */
	public String getErrorcode() {
		return errorcode;
	}

	/**
	 * Message of error occurred while retrieving
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Debug info on the error occurred
	 * 
	 * @return
	 */
	public String getDebuginfo() {
		return debuginfo;
	}

	/**
	 * Represents warnings that come along with a Messages call. <br/>
	 * <br/>
	 * This is not so important to be implemented as main model I guess
	 * 
	 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
	 */
	public class MoodleMessageWarning {
		String item;
		int itemid;
		String warningcode;
		String message;

		/**
		 * Get item
		 * 
		 * @return
		 */
		public String getItem() {
			return item;
		}

		/**
		 * Get item id
		 * 
		 * @return
		 */
		public int getItemid() {
			return itemid;
		}

		/**
		 * Get the warning code can be used by the client app to implement
		 * specific behaviour
		 * 
		 * @return
		 */
		public String getWarningcode() {
			return warningcode;
		}

		/**
		 * Get untranslated english message to explain the warning
		 * 
		 * @return
		 */
		public String getMessage() {
			return message;
		}
	}
}
