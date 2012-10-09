/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import alg_listextract.ListExtract;

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

        ListExtract objectSL = new ListExtract();

        objectSL.split_Lines();
    }
}
