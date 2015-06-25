package com.nvg.core;

import com.nvg.util.FileManagement;
import netscape.javascript.JSObject;

import javax.swing.*;
import java.applet.Applet;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessController;
import java.security.PrivilegedAction;


/**
 * @author GoNun
 */
public class JFileUploaderApplet extends Applet {

    // location of the web site
    private static String _uploadUrl;

    // javascript function to be called for feedback
    private static String _feedback;

    // javascript page object
    private static JSObject _jsObject;

    // server directory to save the file
    private static String _serverDir;


    @Override
    public void init() {
        PrivilegedAction<String> action = new PrivilegedAction<String>() {
            @Override
            public String run() {
                _serverDir = getParameter("serverDir");
                _feedback = getParameter("feedback");
                _uploadUrl = getParameter("uploader");

                _jsObject.eval(String.format("%s();", getParameter("callback")));
                log("Applet ready. (" + _uploadUrl + "," + _serverDir + ")");

                return null;
            }
        };
        _jsObject = JSObject.getWindow(this);
        super.init();
        AccessController.doPrivileged(action);
    }


    /**
     * Log messages to show in the browser console
     *
     * @param message String to be shown in the console
     */
    private static void log(String message) {
        if( !_feedback.matches("noop") ) {
            _jsObject.eval(String.format("%s(\"%s\");", _feedback, message));
        }
    }


    /**
     * Shows the file browser to select the file to upload.
     *
     * @return Absolute path of the selected file
     */
    public static String BrowseFileSystem() {
        PrivilegedAction<String> action = new PrivilegedAction<String>() {
            @Override
            public String run() {
                JFileChooser jFileChooser = new JFileChooser();
                AcceptedFileFilter acceptedFileFilter = new AcceptedFileFilter();
                jFileChooser.setFileFilter(acceptedFileFilter);
                int returnVal = jFileChooser.showOpenDialog(jFileChooser);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jFileChooser.getSelectedFile();
                    return selectedFile.getAbsolutePath();
                }
                return "";
            }
        };
        return AccessController.doPrivileged(action);
    }


    /**
     * Uploads the file passed by parameter to the server
     *
     * @param file File name to set in the server
     * @return true if operation successfull, false otherwise.
     */
    public static boolean UploadFile(final String file) {
        PrivilegedAction<Boolean> action = new PrivilegedAction<Boolean>() {
            @Override
            public Boolean run() {
            try {
                log("Starting Upload...");
                Path path = Paths.get(file);
                byte[] buffer = Files.readAllBytes(path);
                String filename = _serverDir+""+ path.getFileName().toString();
                String uFilename = FileManagement.createUniqueFilename(filename);
                String result = FileManagement.uploadFile(_uploadUrl, buffer, uFilename, "Filename", uFilename);

                if (result != null && !result.equals("")) {
                    log("Upload result: "+ result);
                    return result.startsWith("SUCCESS:");
                } else {
                    log("Upload did not provide a response.");
                    return false;
                }
            } catch (Exception e) {
                String message = "Exception: "+ e.getMessage();
                _jsObject.eval(String.format("%s(\"%s\");", _feedback, message));
                return false;
            }
            }
        };
        return AccessController.doPrivileged(action);
    }
}