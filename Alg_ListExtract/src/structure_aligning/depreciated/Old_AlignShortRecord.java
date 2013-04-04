/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structure_aligning.others;

import alg_listextract.FieldCandidate;
import alg_listextract.FieldExtractor;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import regex_extractor.CurrencyValuesExtractor;
import regex_extractor.DateExtractor;
import regex_extractor.EmailExtractor;
import regex_extractor.PhoneFieldExtractor;
import regex_extractor.TextExtractor;
import regex_extractor.TimeExtractor;
import regex_extractor.UrlsExtractor;
import regex_extractor.ZipCodeBRExtractor;
import regex_extractor.ZipCodeEUAExtractor;
import structure_aligning.Tabela;

/**
 * <CLASSE DEPECRADA!>
 * 
 * This class is responsible for aligning records that contains less columns
 * [with field candidates] than ideal.
 *
 * @since 14/12/2012 - Last change: 05/02/2013
 * @version 0.2
 * @author Juliano R. Macedo
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class Old_AlignShortRecord {

    Tabela tabela = new Tabela();
    List<FieldExtractor> list;

    public Old_AlignShortRecord() {
    }

    /**
     *
     * @param shortRecord
     * @param rowsCadidates
     * @param idealNumColumn
     */
    public void NW_AlignShortRecord(ArrayList<FieldCandidate> shortRecord, ArrayList<ArrayList<FieldCandidate>> rowsCadidates, int idealNumColumn) {

        try {
            double[] vetorScorsShoRec = new double[shortRecord.size()];
            double[] vetorScorsRowsCand = new double[rowsCadidates.size()];
            double matriz[][] = new double[shortRecord.size() + 1][rowsCadidates.size() + 1];

            /**
             * Carregar as listas com os Scores! Primeira coisa a ser feita
             */
            for (int var = 0; var < shortRecord.size(); var++) {

                vetorScorsShoRec[var] = getScoreField(shortRecord.get(var).getField());
            }

            for (int i = 0; i < idealNumColumn; i++) {
                for (int j = 0; j < rowsCadidates.size(); j++) {

                    if (rowsCadidates.get(i).size() == idealNumColumn) {
                        vetorScorsRowsCand[j] = getScoreField(rowsCadidates.get(j).get(i).getField());
                    }
                }
            }

            /*
             * Needleman-Wunsch COMEÇA AQUI!
             * 
             * Carregar a matriz com os valores 'inválidos' [com GAP].
             * 
             * Seguindo o modelo do Artigo, o algoritmo de Needleman-Wunsch utiliza nesse momento a seguinte configuração.
             * Mas seguindo o mesmo algoritmo, so que em outras referências, o UnMatched(fi) e UnMatched(cj) são subistituidos 
             * por um '0' e não existe essa atribuição da propria posição da matriz, dentro dela mesma.
             */
            for (int i = 1; i < shortRecord.size(); i++) {

                matriz[i][0] = (matriz[i - 1][0] + (i * Integer.MAX_VALUE)); // + UnMatched(fi)
            }

            for (int j = 1; j < rowsCadidates.size(); j++) {

                matriz[0][j] = (matriz[0][j - 1] + (j * -1)); // + UnMatched(cj) << Aqui deve ser inserido o campo Nulo [Em todos os UnMatched(cj)]!
            }

            /**
             * Carregar na matriz os valores calculados pelo algortimo de
             * Needleman-Wunsch.
             */
            for (int i = 1; i < vetorScorsShoRec.length; i++) {
                for (int j = 1; j < vetorScorsRowsCand.length; j++) {

                    double scoreLeft = matriz[i][j - 1] + (- 1); // M[i, j − 1] + UnMatched(cj)
                    double scoreUp = matriz[i - 1][j] + (-Integer.MAX_VALUE); // M[i − 1, j] + UnMatched(fi)
                    double scoreDiag = matriz[i - 1][j - 1] + (compareTo(vetorScorsShoRec, vetorScorsRowsCand, i, j)); // M[i − 1, j − 1] +Matched(fi, cj)

                    matriz[i][j] = Math.max(Math.max(scoreLeft, scoreUp), scoreDiag);
                }
            }

            backtrack(shortRecord, rowsCadidates, vetorScorsShoRec, vetorScorsRowsCand, matriz);

            // Print da Matriz, para Debug
            for (int j = 0; j < vetorScorsShoRec.length; j++) {
                int t = 0;
                for (int i = 0; i < vetorScorsRowsCand.length; i++) {

                    if (t < 4) {
                        System.out.print(matriz[j][i] + " | ");
                        t++;
                    } else {
                        System.out.println("");
                        t = 0;
                    }
                }
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
     * @param shortRecord
     * @param rowsCadidates
     * @param vetorScorsShoRec
     * @param vetorScorsRowsCand
     * @param matriz
     */
    private void backtrack(ArrayList<FieldCandidate> shortRecord, ArrayList<ArrayList<FieldCandidate>> rowsCadidates, double[] vetorScorsShoRec, double[] vetorScorsRowsCand, double matriz[][]) {

        try {
            int i = vetorScorsShoRec.length;
            int j = vetorScorsRowsCand.length;
//            String[] vetorSSROrdem = new String[vetorScorsSRec.length];
//            String[] vetorSRCOrdem = new String[vetorScorsRCand.length];

            String AlignmentA = "";
            String AlignmentB = "";

            /**
             * O problema aqui são os '-1' que eu coloquei para subistituir os
             * "d" que nada mas são que os campos nulos [como o alg foi feito
             * para trabalhar com valores, scores "inválidos"]
             */
            while (i > 0 || j > 0) {

                if (i > 0 && j > 0 && matriz[i][j] == matriz[i - 1][j - 1] + compareTo(vetorScorsShoRec, vetorScorsRowsCand, i, j)) {

                    AlignmentA += vetorScorsShoRec[i];
                    AlignmentB += vetorScorsRowsCand[j];
                    i--;
                    j--;

                    // vetorSSROrdem[i] = shortRecord.get(i - 1).getField().toString();//vetorScorsSRec[i-1];
                    // vetorSRCOrdem[j] = rowsCadidates.get(j - 1).get(i).getField();//vetorScorsRCand[j -1];
                    //continue;

                } else if (i > 0 && matriz[i][j] == matriz[i - 1][j] + 0) {

                    AlignmentA += vetorScorsShoRec[i];
                    AlignmentB += "-";
                    i--;

                    //vetorSSROrdem[i] = "Null";
                    // vetorSRCOrdem[j] = rowsCadidates.get(j - 1).get(i).getField();//vetorScorsRCand[j -1];
                    //  continue;
                } else if (j > 0 && matriz[i][j] == matriz[i][j - 1] + 0) {

                    AlignmentA += "-"; //Inserindo campos Nulos!
                    AlignmentB += vetorScorsRowsCand[j];
                    j--;

                    //  vetorSSROrdem[i] = shortRecord.get(i).getField().toString(); //vetorScorsSRec[i-1];
                    //  vetorSRCOrdem[j] = "Null";
                    // continue;
                }
            }

            //AlignmentA = new StringBuffer(AlignmentA).reverse().toString();
            //AlignmentB = new StringBuffer(AlignmentB).reverse().toString();

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
     * @param field
     * @return
     */
    private double getScoreField(String field) {
        try {
            listObjectExtractors(); // É melhor deixá-la aqui ou movê-la para o Singleton??

            for (int j = 0; j < list.size(); j++) {

                double scoreField;
                Pattern patNumVal = Pattern.compile(list.get(j).getExpression());
                Matcher matNumVal = patNumVal.matcher(field);

                while (matNumVal.find()) {

                    scoreField = list.get(j).getScore();
                    return scoreField;
                }
            }
        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
        return 0;
    }

    private void listObjectExtractors() {
        list = new ArrayList<>();

        list.add(new CurrencyValuesExtractor());
        list.add(new DateExtractor());
        list.add(new EmailExtractor());
        list.add(new PhoneFieldExtractor());
        list.add(new TextExtractor());
        list.add(new TimeExtractor());
        list.add(new UrlsExtractor());
        list.add(new ZipCodeBRExtractor());
        list.add(new ZipCodeEUAExtractor());
    }

    /**
     *
     * @param vetorScorsSRec
     * @param vetorScorsRCand
     * @param i
     * @param j
     * @return
     */
    private int compareTo(double[] vetorScorsSRec, double[] vetorScorsRCand, int i, int j) {

        if (vetorScorsSRec[i - 1] == vetorScorsRCand[j - 1]) { //Aqui precisamos acessar os scores
            return 1;
        } else {
            return -1;
        }
    }
}
