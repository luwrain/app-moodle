package org.luwrain.io.moodle.model;

import java.util.*;

import lombok.*;

@Data @NoArgsConstructor
public final class MoodleSiteInfo 
{
    private String sitename;
    private String username;
    private String firstname;
    private String lastname;
    private String fullname;
    private String lang;
    private int userid;
    private String siteurl;
    private String userpictureurl;
    private List<MoodleFunction> functions;
    private int downloadfiles;
    private int uploadfiles;
    private String release;
    private String version;
    private String mobilecssurl;

    private transient String exception;

    /**
     * Errorcode of error occurred while retrieving
     */
    private transient String errorcode;

    /**
     * Message of an error occurred on data retrieving
     */
    private transient String message;

    /**
     * Debug info on the occurred error 
     */
    private transient String debuginfo;

    /*
     * SiteInfo is basically an account Token is needed for an account to get
     * new info from Moodle site
     */
    private String token;

    /**
     * Credentials to be used for login
     */
    private String loginUsername;
    private String loginPassword;

    public MoodleSiteInfo(String loginUsername, String loginPassword, String token)
    {
	this.loginUsername = loginUsername;
	this.loginPassword = loginPassword;
		this.token = token;
    }

    /**
     * Username to be used for site login
     */
    //loginUsername;

    /**
     * Password to be used for site login
     */
    /*
    //loginPassword;

    /**
    * Set token associated with this account
    */
    //token = token;

    /**
     * User profile picture. <br/>
     * Warning: This url is the public URL that only works when forcelogin is
     * set to NO and guestaccess is set to YES. In order to retrieve user
     * profile pictures independently of the Moodle config, replace
     * "pluginfile.php" by "webservice/pluginfile.php?token=WSTOKEN&file=" Of
     * course the user can only see profile picture depending on his/her
     * permissions. Moreover it is recommended to use HTTPS too.
     */
    //userpictureurl;

    /**
     * 1 if users are allowed to download files, 0 if not (Optional)
     */
    //downloadfiles;

    /**
     * 1 if users are allowed to upload files, 0 if not (Optional)
     /*
     //uploadfiles;

     /**
     * Moodle release number (Optional)
     /*
     //release;

     /**
     * Moodle version number (Optional)
     /*
     //version;

     /**
     * Mobile custom CSS theme
     */
    //mobilecssurl;

    /**
     * Get token associated with this account
     /*
     //token;

     /**
     * Exception occurred while retrieving
     */
    //exception;
}
