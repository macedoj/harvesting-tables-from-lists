/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import file_handler.DirectoryConfig;
import file_handler.FileHandler;

/**
 * This is Main Class
 *
 * @since 15/07/2012 - Last change: 09/10/2012
 * @version 0.1
 * @author Juliano R. Macedo
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class Main_ListExtract {

    @SuppressWarnings("CallToThreadDumpStack")
    public static void main(String args[]) {

        try {

            /**
             * Define path of files to extract.
             */
            DirectoryConfig.getInstance().setExtractDirectory("files\\lists\\");

            /**
             * Define path of out file [After extraction]
             */
            DirectoryConfig.getInstance().setOutDirectory("files\\outLists\\");

            /**
             * Call the fileHandler
             */
            FileHandler fileHandler = new FileHandler();
            fileHandler.listFileInDirectory();


        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
            //System.out.println(" Exception: " + error);
        }
    }
}
