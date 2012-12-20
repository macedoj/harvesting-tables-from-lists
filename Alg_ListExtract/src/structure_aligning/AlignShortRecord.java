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

        ArrayList<ArrayList<Score>> arrayScores;
        int numLinhas = rowsCadidates.size();

        try {
            arrayScores = new ArrayList<>(idealNumColumn);

            for (int j = 0; j < idealNumColumn; j++) {

                int repeticoes = 0;
                float percent = 0;

                for (int i = 0; i < rowsCadidates.size(); i++) {

                    if (rowsCadidates.get(i).size() == idealNumColumn) {

                        String fieldCorrectColumn = rowsCadidates.get(i).get(j).getField();

                        if (field.equals(fieldCorrectColumn)) {

                            repeticoes++;
                            System.out.println(field + " == " + fieldCorrectColumn + " < Num coluna:" + j);
                        }
                    }
                }

                if (repeticoes > 0) {

                    percent = (computePercentage(repeticoes, numLinhas));
                    System.out.println(numLinhas + " | " + repeticoes + " \nColuna: " + j + " - Igualidaddes: " + percent + "%");
                }

                Score s = new Score();
                s.addScoreNoArray(percent);
                carregaInfos(arrayScores, field, j, percent, s);



                /*if (repeticoes > maxRepeticoes) {

                 System.out.println("Repetição total: " + repeticoes);
                 posicaoDaColuna = j;
                 maxRepeticoes = repeticoes;
                 }
                 float scoreAtual = (((float) maxRepeticoes) / numLinhas);
                 System.out.println("Field: " + field);
                 System.out.println("Maior Score: " + scoreAtual + " - Coluna: " + posicaoDaColuna);
                 */
            }
        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
    }

    /**
     *
     * @param repeticoes number of occurrences
     * @param numLinhas number of line
     * @return the percentage of equals fields.
     */
    public float computePercentage(int repeticoes, int numLinhas) {

        return ((float) (repeticoes * 100) / numLinhas);
    }

    public void carregaInfos(ArrayList<ArrayList<Score>> arrayScores, String field, int numColumn, float percent, Score s) {

        arrayScores.add(numColumn, s.obterArrayScores());

        System.out.println(arrayScores + " - ");

        /*  
         //carregar as informações dentro da estrutura de classes.
         Valor v = new Valor();
         Coluna c = new Coluna();
         Score s = new Score();

         c.criaColuna();
         v.setValor(field);
         v.defineScoreDoValor(percent, s, c);

         c.exibirTabela();
         */
    }
}