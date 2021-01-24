
package org.luwrain.io.moodle;

import java.io.*;
import java.util.*;

import org.junit.*;

import org.luwrain.core.*;
import org.luwrain.io.moodle.model.*;
import org.luwrain.io.moodle.rest.*;

public class TokenTest extends Base
{
    @Test public void getToken() throws IOException
    {
	loadProps();
	assertTrue(isReadyPasswd());
	final MoodleRestToken restToken = new MoodleRestToken(this.url, this.user, this.passwd);
	final MoodleToken token = restToken.getToken();
	assertNotNull(token);
	System.out.println("proba " + token.getToken());
    }
}
