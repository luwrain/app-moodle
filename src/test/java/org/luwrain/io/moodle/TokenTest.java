
package org.luwrain.io.moodle;

import java.io.*;
import java.util.*;

import org.junit.*;

import org.luwrain.core.*;
import org.luwrain.io.moodle.model.*;
import org.luwrain.io.moodle.rest.*;

public class TokenTest extends Base
{
    @Ignore @Test public void getToken() throws IOException
    {
	loadProps();
	assertTrue(isReadyPasswd());
	final RestToken restToken = new RestToken(this.url, this.user, this.passwd);
	final Token token = restToken.getToken(RestToken.MOBILE);
		assertNotNull(token);
		assertEquals("", token.getErrorInfo());
	System.out.println("proba " + token.getToken());
    }
}
