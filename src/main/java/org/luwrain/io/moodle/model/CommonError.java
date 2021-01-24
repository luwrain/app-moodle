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

import com.google.gson.annotations.*;
import lombok.*;

@Data
@NoArgsConstructor
public class CommonError
{
    @SerializedName("error")
    private String error = null;

    @SerializedName("errorcode")
    private String errorCode = null;

    @SerializedName("stacktrace")
    private String stackTrace = null;

    @SerializedName("debuginfo")
    private String debugInfo;

    @SerializedName("reproductionlink")
    private String reproductionLink = null;

    public boolean isOk()
    {
	return errorCode == null || errorCode.isEmpty();
    }

    public String getErrorInfo()
    {
	if (error == null || error.isEmpty())
	    return "";
	final StringBuilder b = new StringBuilder();
	b.append(error.trim());
	if (errorCode != null && !errorCode.trim().isEmpty())
	    b.append(", code: ").append(errorCode);
	if (debugInfo != null && !debugInfo.trim().isEmpty())
	    b.append(", ").append(debugInfo.trim());
	return new String(b);
    }
}
