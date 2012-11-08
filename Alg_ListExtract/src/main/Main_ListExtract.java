/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import file_handler.FileHandler;
import structure_aligning.Table;

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
            Table t = new Table();
            t.teste1();
            t.linhas();
            
            
/**
            String directory = "files\\lists\\";
            String nameFile = "listExemple.txt";
            FileHandler fileHandler = new FileHandler();

//            fileHandler.listFileInDirectory(directory);
            fileHandler.splitFile(nameFile);
*/
        } catch (Exception error) {

            System.out.println(" Exception: " + error);
        }
    }
}
