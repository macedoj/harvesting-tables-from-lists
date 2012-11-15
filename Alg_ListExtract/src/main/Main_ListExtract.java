/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import file_handler.FileHandler;
import structure_aligning.Coluna;
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

            Valor v = new Valor();

            v.setValor("01");
            v.adiconarColuna(9.5);
            v.addScoreIncolumn(0, 10.7);
            
            v.exibirTabela();

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


             /**
             * String directory = "files\\lists\\"; String nameFile =
             * "listExemple.txt"; FileHandler fileHandler = new FileHandler();
             *
             * // fileHandler.listFileInDirectory(directory);
             * fileHandler.splitFile(nameFile);
             */
        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
            //System.out.println(" Exception: " + error);
        }
    }
}
