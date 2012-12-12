/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import file_handler.FileHandler;
import structure_aligning.Coluna;
import structure_aligning.Score;
import structure_aligning.Valor;

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
//
/*
            Valor v = new Valor();
            Score s = new Score();
            Coluna c = new Coluna();
            c.criaColuna();
            v.setValor("teste");
            v.defineScoreDoValor(10.2, s, c);
            v.defineScoreDoValor(9.2, s, c);
            v.defineScoreDoValor(8.2, s, c);
            v.defineScoreDoValor(7.2, s, c);
            v.defineScoreDoValor(6.2, s, c);
            
//            v.defineScoreDoValor(10.7, 11.5, 2.7, 0, c);
//            v.defineScoreDoValor(6.0, 7.0, 4.0, 1, c);
            c.exibirTabela();
            
            Valor v2 = new Valor();
            Score s2 = new Score();
            Coluna c2 = new Coluna();
            c2.criaColuna();
            v2.setValor("teste C2");
            v2.defineScoreDoValor(0.1, s2, c2);
            v2.defineScoreDoValor(0.2, s2, c2);
            v2.defineScoreDoValor(0.3, s2, c2);
            v2.defineScoreDoValor(0.4, s2, c2);            
            
//            v4.defineScoreDoValor(0.4, 0.5, 0.6, 2, c);
//            v4.defineScoreDoValor(0.1, 0.2, 0.3, 0, c2);
            System.out.println("Tabela 1");
            c.exibirTabela();
            System.out.println("Tabela 2");
            c2.exibirTabela();
            
//            v.defineScoreDoValor(7.5, 2.1, 3.4, 0, c);
            System.out.println("Tabela 1");
            v.defineScoreDoValor(0.12345, s, c);
            c.exibirTabela();


*/



//            Valor_v2 v = new Valor_v2();
//
//            v.setValor("01");
//            v.adiconarColuna(9.5);
//            v.addScoreIncolumn(0, 10.7);
//            
//            v.exibirTabela();

            /*
             Coluna_v1 objCol = new Coluna_v1();

             objCol.setScore(1.0);
             objCol.setScore(2.0);
             objCol.setScore(3.0);

             Valor_v2 objVal = new Valor_v2();

             objVal.setValor("abc");
             objVal.construirColuna(objCol);
             System.out.println("Tabela 1: " + objVal.exibirTabela());

             Coluna_v1 objCol2 = new Coluna_v1();

             objCol2.setScore(4.0);
             objCol2.setScore(5.0);
             objCol2.setScore(6.0);

             objVal.construirColuna(objCol2);
             System.out.println("Tabela 2: " + objVal.exibirTabela());

             Coluna_v1 objCol3 = new Coluna_v1();

             objCol3.setScore(7.0);
             objCol3.setScore(8.0);
             objCol3.setScore(9.0);

             objVal.construirColuna(objCol3);
             System.out.println("Tabela 3: " + objVal.exibirTabela());

*/
             // Capturar o path do arquivo desejado.
              String directory = "files\\lists\\ListasExemploSIEPE";
              //String directory = "files\\lists\\";
             // String nameFile = "ListaFilmesIndicadosOscar2012.txt"; 
              FileHandler fileHandler = new FileHandler();
             
              // fileHandler.listFileInDirectory(directory);
              fileHandler.listFileInDirectory(directory);
             
        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
            //System.out.println(" Exception: " + error);
        }
    }
}
