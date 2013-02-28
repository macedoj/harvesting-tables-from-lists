/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structure_aligning;

import alg_listextract.FieldCandidate;
import alg_listextract.FieldExtractor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    List<FieldExtractor> list;

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
    public void geraMatrizScors(ArrayList<FieldCandidate> shortRecord,
            ArrayList<ArrayList<FieldCandidate>> rowsCadidates, int idealNumColumn) {

        ArrayList<ArrayList<FieldCandidate>> correctColumns = new ArrayList<>();

        //Carregando a lista apenas com as colunas corretas (=idealNumColumn)
        for (int i = 0; i < rowsCadidates.size(); i++) {

            if (rowsCadidates.get(i).size() == idealNumColumn) {
                correctColumns.add(rowsCadidates.get(i));
            }
        }

        //Dimensão 0, são os valores, na dimensao 1, são as colunas
        double[][] matrizScors = new double[shortRecord.size()][idealNumColumn];
        double percentagem = 0;

        for (int j = 0; j < shortRecord.size(); j++) {
            for (int i = 0; i < idealNumColumn; i++) {

                if (correctColumns.get(i).size() == idealNumColumn) {

                    int soma = 0;
                    int tamColuna = 0;
                    tamColuna = correctColumns.size(); // Número de Linhas de cada coluna

                    for (int t = 0; t < correctColumns.size(); t++) {

                        if (correctColumns.get(i).size() == idealNumColumn) {
                            //Recebe o retor binário depois do cálculo.
                            soma += funcaoCompara(shortRecord.get(j).getField(), correctColumns.get(t).get(i).getField());
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
        // printaMatriz(matrizScors);
        //calcRankingExpress(correctColumns, idealNumColumn);
//        
//        System.out.println("Field: "+correctColumns.get(0).get(0).getField());
//        System.out.println("->> "+ getScoreField(correctColumns.get(0).get(0).getField()));
//        System.out.println("------");
//        System.out.println("Field: "+correctColumns.get(2).get(2).getField());
//        System.out.println("->> "+ getScoreField(correctColumns.get(3).get(4).getField()));


        System.out.println("Data: " + getScoreField("21/12/2012"));



        /**
         * Aqui vai o resto da constiaç
         */
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

    private void calcRankingExpress(ArrayList<ArrayList<FieldCandidate>> correctColumns, int idealNumColumn) {
        try {

            HashMap<Integer, String> mapExpressions = new HashMap<>();
            String betterExpression = null;

            for (int i = 0; i < idealNumColumn; i++) {
                for (int j = 0; j < correctColumns.size(); j++) {

                    String field = correctColumns.get(j).get(i).getField();
                    String verReturn = getScoreField(field);

                    if (!verReturn.isEmpty()) {
                        System.out.print(" map: [" + j + "]"
                                + mapExpressions.put(j, verReturn));
                    }
                    System.out.println("");
                }
            }

            if (!mapExpressions.isEmpty()) {

                Collection<String> valuesofMap;
                valuesofMap = mapExpressions.values();
                String high = Collections.max(valuesofMap);

                for (Map.Entry<Integer, String> entry : mapExpressions.entrySet()) {

                    if (high.equals(entry.getValue())) {

                        betterExpression = entry.getValue();
                    }
                }
            }

            System.out.println(betterExpression);

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
    private String getScoreField(String field) {
        try {
            listObjectExtractors(); // É melhor deixá-la aqui ou movê-la para o Singleton??

            for (int j = 0; j < list.size(); j++) {

                String expField;
                Pattern patNumVal = Pattern.compile(list.get(j).getExpression());
                Matcher matNumVal = patNumVal.matcher(field);

                while (matNumVal.find()) {
                    expField = list.get(j).getNameExpression();
                    return expField;
                }
            }
        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
        return null;
    }

    private void listObjectExtractors() {
        try {
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
        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
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
