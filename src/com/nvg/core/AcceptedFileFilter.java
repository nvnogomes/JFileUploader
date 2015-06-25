package com.nvg.core;

import com.nvg.util.FileExtention;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Defines the types of objects to listed and
 * accepted in the JFileChooser
 *
 * @author GoNun
 */
class AcceptedFileFilter extends FileFilter {

    /**
     * Types of file to accept
     *
     * @param f file object
     * @return true if acceptable, false otherwise
     */
    @Override
    public boolean accept(File f) {
        if( f.isDirectory()) {
            return true;
        }
        String fileType = FileExtention.getExtension(f);
        switch(fileType) {
            case FileExtention.PDF:
            case FileExtention.JPEG:
            case FileExtention.JPG:
            case FileExtention.GIF:
            case FileExtention.PNG:
            case FileExtention.DOC:
            case FileExtention.DOCX:
            case FileExtention.XLS:
            case FileExtention.XLSX:
            case FileExtention.ODT:
            case FileExtention.ODS:
            case FileExtention.ZIP:
            case FileExtention.TXT:
                return true;
            default:
                return false;
        }
    }

    /**
     * String to be used in the GUI
     *
     * @return string with acceptable extensions
     */
    @Override
    public String getDescription() {
        return "doc, docx, xls, xlsx, txt, odt, ods, pdf, jpeg, gif, png, zip";
    }
}
