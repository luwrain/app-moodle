package in.co.praveenkumar.mdroid.helper;

import java.io.*;

public class FileOpener {

	/**
	 * Opens a file. Shows a toast when opening failed.
	 * 
	 * @param context
	 * @param file
	 */
	public static void open(Object context, String file) {
		open(context, new File(file));
	}

	/**
	 * Opens a file. Shows a toast when opening failed.
	 * 
	 * @param context
	 * @param file
	 */
	public static void open(Object context, File file)
    {
	throw new RuntimeException("Not implemented");
	}

}
