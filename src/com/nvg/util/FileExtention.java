package com.nvg.util;

import java.io.File;

/**
 * @author GoNun
 */
public class FileExtention {

    // images
    public final static String JPEG = "jpeg";
    public final static String JPG = "jpg";
    public final static String GIF = "gif";
    public final static String PNG = "png";

    // office
    public final static String DOC = "doc";
    public final static String DOCX = "docx";
    public final static String XLS = "xls";
    public final static String XLSX = "xlsx";
    public final static String ODT = "odt";
    public final static String ODS = "ods";

    // others
    public final static String PDF = "pdf";
    public final static String ZIP = "zip";
    public final static String TXT = "txt";




    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
       return ext;
    }
}
