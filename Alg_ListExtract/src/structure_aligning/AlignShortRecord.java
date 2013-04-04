/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structure_aligning;

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

/**
 * <p>
 * This class is responsible for aligning records that contains less columns
 * <tt>[with field candidates]</tt> than ideal.
 * </p>
 *
 * @author Juliano R. Macedo
 * @version 0.1
 * @since 06/02/2013 - Last change: 28/03/2013
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class AlignShortRecord {

    private static AlignShortRecord objAlgnSR = null;
    private List<FieldExtractor> list;

    /**
     * Don't let anyone instantiate this class.
     */
    private AlignShortRecord() {
        list = new ArrayList<>();
    }

    /**
     * <p>
     * This is Singleton method of class AlignShortRecord!
     * </p>
     *
     * Depois da última atualização da classe, onde o algoritmo de Nedleman foi
     * removido, não é mais necessário esse método singleton.
     *
     * @return the instance of this class
     */
    public static AlignShortRecord getInstance() {
        if (objAlgnSR == null) {
            objAlgnSR = new AlignShortRecord();
        }
        return objAlgnSR;
    }

    public void geraMatrizScors(ArrayList<FieldCandidate> shortRecord,
            ArrayList<ArrayList<FieldCandidate>> rowsCadidates, int idealNumColumn) {

        ArrayList<ArrayList<FieldCandidate>> correctColumns = new ArrayList<ArrayList<FieldCandidate>>();
        Score score = Score.getInstance();
        Line line = new Line();
        Value value = new Value();
        Table table = new Table();
        double equalsFieldScore = 0;
        double scoreBasedExpressions = 0;

        for (int i = 0; i < rowsCadidates.size(); i++) {
            if (rowsCadidates.get(i).size() == idealNumColumn) {
                correctColumns.add(rowsCadidates.get(i));
            }
        }

        for (int i = 0; i < shortRecord.size(); i++) {
            boolean mustAdd = false;

            for (int j = 0; j < idealNumColumn; j++) {

                if (correctColumns.get(j).size() == idealNumColumn) {
                    mustAdd = true;
                    double occurrencesSum = 0;
                    int numberLinesCorrectColumns = 0;
                    numberLinesCorrectColumns = correctColumns.size();

                    for (int t = 0; t < correctColumns.size(); t++) {
                        if (correctColumns.get(j).size() == idealNumColumn) {
                            occurrencesSum += funcaoCompara(shortRecord.get(i).getField(), correctColumns.get(t).get(j).getField());
                            value.setValor(shortRecord.get(i).getField());
                        }
                    }
                    equalsFieldScore = computePercentage(occurrencesSum, numberLinesCorrectColumns);
                    scoreBasedExpressions = scoreBasedExpressions(correctColumns, idealNumColumn, shortRecord.get(i).getField());

                    /**
                     * Verificar a construção da list de scores na classe Line!
                     */
                    score.setScore(equalsFieldScore);
                    line.addScore(score); //Add o Score que acabou de ser definido.
                    System.out.println(">" + line.getScores());

                    if (scoreBasedExpressions != Double.MAX_VALUE) {
                        score.setScore(scoreBasedExpressions);
                        line.addScore(score);
                        System.out.println(line.getScores());
                    } else;
                }
            }
            if (mustAdd) {
                value.addLine(line);
//                System.out.println(line.getScores());
//                System.out.println(value.getLines());
                table.addValue(value);
//                System.out.println(table.getValuesScores());
            }
        }
        //        System.out.println(table.getValuesScores());

        System.out.println("------");
//        printaMatriz(matrizScors);
    }

    /**
     *
     * @param field
     * @param columnField
     * @return a porcentagem da comparação
     */
    private double funcaoCompara(String field, String columnField) {

        double porcentagemComparacao = 0;

        if (field.equals(columnField)) {

            porcentagemComparacao = 1;
            return porcentagemComparacao;
        } else {
            return porcentagemComparacao;
        }
    }

    /**
     * <p>
     * Compute the percentage of similarity the field for the others fields.
     * </p>
     *
     * @param occurrencesSum
     * @param numberLinesCorrectColumns
     * @return the percentage of equals fields.
     */
    private double computePercentage(double occurrencesSum, int numberLinesCorrectColumns) {
        return ((double) ((occurrencesSum * 100) / numberLinesCorrectColumns));
    }

    @SuppressWarnings("CallToThreadDumpStack")
    private String getFieldExpression(String field) {
        try {
            listObjectExtractors(); //>>>> Sérgio:  É melhor deixá-la aqui ou movê-la para o Singleton?? <<<<

            if (field != null) {
                for (int j = 0; j < list.size(); j++) {

                    String expressionField;
                    Pattern patNumVal = Pattern.compile(list.get(j).getExpression());
                    Matcher matNumVal = patNumVal.matcher(field);

                    while (matNumVal.find()) {
                        expressionField = list.get(j).getNameExpression();
//                    System.out.println(field +" = " + expressionField +" ?");
                        return expressionField;
                    }
                }
            } else {
                System.out.println("> ERROR: [In 'getFieldExpression' Method] >> The 'String field' parameter is Null!");
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

    @SuppressWarnings("CallToThreadDumpStack")
    private void listObjectExtractors() {
        try {
            list.add(new ZipCodeBRExtractor()); // 0.9
            list.add(new EmailExtractor()); //0.85
            list.add(new PhoneFieldExtractor()); // 0.8
            list.add(new ZipCodeEUAExtractor()); // 0.75
            list.add(new DateExtractor()); // 0.7
            list.add(new TimeExtractor()); // 0.65
            list.add(new UrlsExtractor()); // 0.6
            list.add(new CurrencyValuesExtractor()); //0.2
            list.add(new TextExtractor()); // 0.1
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
     * @param correctColumns
     * @param idealNumColumn
     * @param field
     * @return
     */
    @SuppressWarnings("CallToThreadDumpStack")
    private double scoreBasedExpressions(ArrayList<ArrayList<FieldCandidate>> correctColumns, int idealNumColumn, String field) {
        try {

            String fieldExpression = null;
            ArrayList<ArrayList<String>> allColumnExpressions = new ArrayList<ArrayList<String>>();

            fieldExpression = getFieldExpression(field);
            allColumnExpressions = expressionsInColumns(correctColumns, idealNumColumn);

            if (!allColumnExpressions.isEmpty()) {
                int occurrencesSum = 0;

                for (int i = 0; i < idealNumColumn; i++) {
                    for (int j = 0; j < allColumnExpressions.size(); j++) {
                        occurrencesSum += funcaoCompara(fieldExpression, allColumnExpressions.get(i).get(j));
                    }
                }
                double expressionScore = 0.0;
                expressionScore = computePercentage(occurrencesSum, allColumnExpressions.size());

                return expressionScore;
            }
        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug].
             */
            error.printStackTrace();
//            System.out.println(error);
        }
        return Double.MAX_VALUE;
    }

    /**
     *
     * @param correctColumns
     * @param idealNumColumn
     * @return
     */
    @SuppressWarnings("CallToThreadDumpStack")
    private ArrayList<ArrayList<String>> expressionsInColumns(ArrayList<ArrayList<FieldCandidate>> correctColumns, int idealNumColumn) {
        try {

            ArrayList<ArrayList<String>> allColumnExpressions = new ArrayList<ArrayList<String>>();

            for (int i = 0; i < idealNumColumn; i++) {
                ArrayList<String> columnExpressions = new ArrayList<String>();
                for (int j = 0; j < correctColumns.size(); j++) {

                    String fieldExpression = getFieldExpression(String.valueOf(correctColumns.get(j).get(i)));
                    columnExpressions.add(fieldExpression);
                }
                allColumnExpressions.add(i, columnExpressions);
            }

            return allColumnExpressions;

        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
//            System.out.println(error);
        }
        return null;
    }
}
