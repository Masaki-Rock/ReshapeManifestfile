package com.tools.format;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tools.format.util.CMN_Const;
import com.tools.format.util.CMN_PackageXMLWriter;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		CMN_PackageXMLWriter util = new CMN_PackageXMLWriter();
		List<CMN_MetaDto> mlist = util.read(CMN_Const.MANIFEST_FILE_PATH);
		util.create(CMN_Const.MANIFEST_CHG_FILE_PATH, mlist);
	}

}
