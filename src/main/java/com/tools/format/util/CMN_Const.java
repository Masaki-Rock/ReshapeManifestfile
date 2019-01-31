package com.tools.format.util;

import java.io.File;

/**
 * 定数クラス Date --- Author ---- Subject 
 * 20160503 M.Kawaguchi プログラムリリース
 **/
public class CMN_Const {

	private CMN_Const() {
	}

	public static final String MANIFEST_FILE = "package.xml";
	
	public static final String MANIFEST_CHG_FILE = "package-chg.xml";
	
	public static String GIT_SETTING_DIR = CMN_Util.pathChr() + ".git";

	public static String GIT_DIR = CMN_Util.pathChr() + "dist";

	public static String CURRENT_PATH = new File("").getAbsolutePath();

	public static String SRC_PATH = "src" + CMN_Util.pathChr();

	public static final String MANIFEST_FILE_PATH = SRC_PATH + MANIFEST_FILE;
	
	public static final String MANIFEST_CHG_FILE_PATH = CURRENT_PATH + CMN_Util.pathChr() + SRC_PATH + MANIFEST_CHG_FILE;
	/** API VERSION */
	public static final String VERSION_API = "43.0";
}
