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

package org.luwrain.io.moodle.rest;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.annotations.SerializedName;

/**
 * Why?
 * Because some of the field names in a model may conflict with those from Sugar class. 
 * Simply adding a serialize command to those fields will throw exception listed below.
 * 
 * So, we add serializedName to all fields in such models and decode only those with 
 * serializedName using this Gson exclusion strategy. 
 * 
 * Exception: http://stackoverflow.com/questions/19315431/gson-tostring-gives-error-illegalargumentexception-multiple-json-fields-name
 */

/**
 * This ignores all the fields without a serializeName from decoding
 * 
 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
 * 
 */
final class GsonExclude implements ExclusionStrategy
{

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes field) {
		SerializedName ns = field.getAnnotation(SerializedName.class);
		if (ns != null)
			return false;
		return true;
	}

}
