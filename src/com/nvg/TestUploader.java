package com.nvg;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class description
 *
 * @author GomesNun
 * @version 201506.18001
 */
class TestUploader {

    public static void main(String[] args) {

        String file = "d:/temp/sample.pdf";
        Path pathy = Paths.get(file);
        System.out.println( pathy.getFileName().toString() );

        /*try {
            Path path = Paths.get("d:/temp/Template.pdf");
            byte[] buffer = Files.readAllBytes(path);
            System.out.println(buffer.length);
            String filename = "c:/import/sampleCopy2.pdf";
            String result = FileManagement.uploadFile("http://localhost/UploadService/upload.aspx", buffer, filename, "Filename", filename);
            System.out.println(result);
        } catch(Exception e) {
            e.printStackTrace();
        }*/

    }
}
