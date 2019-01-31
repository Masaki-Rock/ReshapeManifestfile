package com.tools.format.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 汎用ユーティリティークラス
 * Date --- Author ---- Subject
 * 20160503 M.Kawaguchi プログラムリリース
 **/
public class CMN_Util {

    private static final Logger logger = LoggerFactory.getLogger(CMN_Util.class);

    private CMN_Util() {
    }

    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();

    /**
     * 
     * @return 階層文字を取得します
     */
    public static String pathChr() {
        if (OS_NAME.startsWith("linux") || OS_NAME.startsWith("mac") || OS_NAME.startsWith("ubuntu")) {
            return "/";
        }
        return "\\";
    }
}
