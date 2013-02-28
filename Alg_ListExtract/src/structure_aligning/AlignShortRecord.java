/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structure_aligning;

import alg_listextract.FieldCandidate;
import java.util.ArrayList;

/**
 * This class is responsible for aligning records that contains less columns
 * [with field candidates] than ideal.
 *
 * @since 06/02/2013 - Last change: 14/02/2013
 * @version 0.1
 * @author Juliano R. Macedo
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class AlignShortRecord {

    private static AlignShortRecord objAlgnSR;

    public AlignShortRecord() {
    }

    /**
     * Depois da última atualização da lasse, onde o algoritmo de Nedleman foi
     * removido, não é mais necessário esse método singleton.
     *
     * @see This is Singleton method of class AlignShortRecord!
     * @return the instance of this class
     */
    public static AlignShortRecord getInstance() {

        if (objAlgnSR == null) {
            objAlgnSR = new AlignShortRecord();
        }
        return objAlgnSR;
    }

    /**
     *
     * @param shortRecord
     * @param rowsCadidates
     * @param idealNumColumn
     */
    public void geraMatrizScors(ArrayList<FieldCandidate> shortRecord, ArrayList<ArrayList<FieldCandidate>> rowsCadidates, int idealNumColumn) {

        ArrayList<ArrayList<FieldCandidate>> correctColumns = new ArrayList<>();
        
        //Carregando a lista apenas com as colunas corretas (=idealNumColumn)
        for (int i = 0; i <rowsCadidates.size(); i++) {
            
            if(rowsCadidates.get(i).size() == idealNumColumn){
            correctColumns.add(rowsCadidates.get(i));
            }
            
        }

        //Dimensão 0, são os valores, na dimensao 1, são as colunas
        double[][] matrizScors = new double[shortRecord.size()][idealNumColumn];
        double percentagem = 0;

        for (int j = 0; j < shortRecord.size(); j++) {

            for (int i = 0; i < idealNumColumn; i++) {

                if (rowsCadidates.get(i).size() == idealNumColumn) {

                    int soma = 0;
                    int tamColuna = 0;
                    tamColuna = rowsCadidates.size(); // Número de Linhas de cada coluna

                    for (int t = 0; t < rowsCadidates.size(); t++) {

                        if (rowsCadidates.get(i).size() == idealNumColumn) {
                            //Recebe o retor binário depois do cálculo.
                            soma += funcaoCompara(shortRecord.get(j).getField(), rowsCadidates.get(t).get(i).getField());
                            /// No "rowsCadidates.get(t).get(i).getField()" o get(i) gera exceção dentro de linhas 
                            // que contem menor quantidade de valores. Gerar a tabela com as linhas que contem os numeros de campos validos.
                        }
                    }
                    /**
                     * Olhar o excel para realizar as alterações.
                     */

                    percentagem = computePercentage(soma, tamColuna);
                    matrizScors[j][i] = percentagem;
                }
            }
        }
        System.out.println("------");
         printaMatriz(matrizScors);
    }

    /**
     *
     * @param campo
     * @param campoDaColuna
     * @return
     */
    private int funcaoCompara(String campo, String campoDaColuna) {

        int porcentagemComparacao = 0;

        if (campo.equals(campoDaColuna)) {

            porcentagemComparacao = 1;
            return porcentagemComparacao;
        } else {
            return porcentagemComparacao;
        }
    }

    /**
     * Compute the percentage of similarity the field for the others fields.
     *
     * @param soma
     * @param tamColuna
     * @return the percentage of equals fields.
     */
    private double computePercentage(int soma, int tamColuna) {

        return ((double) ((soma * 100) / tamColuna));
    }

    private void printaMatriz(double[][] matrizScors) {

        for (int j = 0; j < 4; j++) {
            int t = 0;
            for (int i = 0; i < 5; i++) {

                if (t <= 4) {
                    System.out.print(matrizScors[j][i] + " | ");
                    t++;
                }
            }
            System.out.println("");
        }
    }
}
