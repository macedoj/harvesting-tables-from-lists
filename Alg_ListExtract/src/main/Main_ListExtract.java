/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import alg_listextract.ListExtract;
import file_handler.FileHandler;

/**
 * This is a Main Class...
 *
 * @since 15/07/2012 - Last change: 09/10/2012
 * @version 0.1
 * @author Juliano R. Macedo
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class Main_ListExtract {

    /**
     * Main simples do algoritmo
     *
     * @param args
     */
    public static void main(String args[]) {

        try {

            //ListExtract objectSL = new ListExtract();
            FileHandler fileHandler = new FileHandler();
            fileHandler.listFileInDirectory();

           // objectSL.split_Lines();

        } catch (Exception error) {

            System.out.println(" Exception: " + error);
        }
    }
}
