/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structure_aligning;

import alg_listextract.FieldCandidate;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class AlignShortRecord {

    public AlignShortRecord() {
    }

    /**
     * Criar um método que receba uma tipo de dado e um conjunto de tipos vindos
     * de uma das colunas corretas, o seu retorno será uma pontuação que indique
     * a semelhança do tipo com os tipos daquela coluna[correta].
     *
     * @param field the field for comparison
     * @param rowsCadidates the all candidates fields == idealNumColumn
     * @param idealNumColumn the number of ideal columns
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public void pre_AlignShortRecord(String field, ArrayList<ArrayList<FieldCandidate>> rowsCadidates, int idealNumColumn) {

        int maxRepeticoes = 0;
        int posicaoDaColuna = 0;
        int numLinhas = rowsCadidates.size();
        // System.out.println("\nCampo: " + field + "[" + correctColumn.size() + "]");

        try {
            for (int j = 0; j < idealNumColumn; j++) {

                int repeticoes = 0;
                for (int i = 0; i < rowsCadidates.size(); i++) {

                    if (rowsCadidates.get(i).size() == idealNumColumn) {

                        String fieldCorrectColumn = rowsCadidates.get(i).get(j).getField();
                        //   System.out.println("Other field: " + fieldCorrectColumn);

                        if (field.equals(fieldCorrectColumn)) {

                            repeticoes++;
                            System.out.println(field + " == " + fieldCorrectColumn + " < Num coluna:" + posicaoDaColuna);
                        }
                    }
                }

                if (repeticoes > maxRepeticoes) {

                    System.out.println("Repetição total: " + repeticoes);
                    posicaoDaColuna = j;
                    maxRepeticoes = repeticoes;
                }
            }

            float scoreAtual = (((float) maxRepeticoes) / numLinhas);
            System.out.println("Field: " + field);
            System.out.println("Maior Score: " + scoreAtual + " - Coluna: " + posicaoDaColuna);

        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
    }
}
