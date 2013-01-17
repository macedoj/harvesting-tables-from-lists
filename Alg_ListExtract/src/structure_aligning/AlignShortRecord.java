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
 * This class is responsible for aligning records that contains less columns
 * [with field candidates] than ideal.
 *
 * @since 14/12/2012 - Last change: 18/12/2012
 * @version 0.2
 * @author Juliano R. Macedo
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class AlignShortRecord {

    Tabela tabela = new Tabela();
    List<FieldExtractor> list;
    //private FieldExtractor objFieldExt;

    public AlignShortRecord() {
    }

    /**
     * Criar um método que receba uma tipo de dado e um conjunto de tipos vindo
     * de uma coluna correta, o seu retorno será uma pontuação que indique a
     * semelhança do tipo com os tipos daquela coluna[correta].
     *
     * @param field the field for comparison
     * @param rowsCadidates the all candidates fields == idealNumColumn
     * @param idealNumColumn the number of ideal columns
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public void pre_AlignShortRecord(String field, ArrayList<ArrayList<FieldCandidate>> rowsCadidates, int idealNumColumn) {

        ArrayList<ArrayList<Score>> arrayScores;

        Valor valor = new Valor();
        valor.setValor(field);
        tabela.addValor(valor);

        try {
            arrayScores = new ArrayList<>(idealNumColumn);

            for (int j = 0; j < idealNumColumn; j++) {

                Coluna objColumn = new Coluna();
                objColumn.setNome(String.valueOf(j));
                valor.addColuna(objColumn);

                computeScoreEquals(field, objColumn, rowsCadidates, idealNumColumn, j);
            }
        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
    }

    @SuppressWarnings("CallToThreadDumpStack")
    private void computeScoreEquals(String field, Coluna objColumn, ArrayList<ArrayList<FieldCandidate>> rowsCadidates, int idealNumColumn, int posiColumn) {
        int repeticoes = 0;
        float percent = 0;
        int numLinhas = rowsCadidates.size();
        try {

            for (int i = 0; i < rowsCadidates.size(); i++) {

                if (rowsCadidates.get(i).size() == idealNumColumn) {

                    String fieldCorrectColumn = rowsCadidates.get(i).get(posiColumn).getField();

                    if (field.equals(fieldCorrectColumn)) {
                        objectExtractors();
                        extractField(field);
                        repeticoes++;
                        System.out.println(field + " == " + fieldCorrectColumn + " < Num coluna:" + objColumn.getNome());
                    }
                }
            }

            if (repeticoes > 0) {

                percent = (computePercentage(repeticoes, numLinhas));
                System.out.println(numLinhas + " | " + repeticoes + " \nColuna: " + objColumn.getNome() + " - Igualidaddes: " + percent + "%");
            }

            // Score s = new Score();
            Score.getInstance().setScore(percent);
            objColumn.addScore();

        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
    }

    public void objectExtractors() {
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

        //return list;
    }

    /**
     * Compute the percentage of similarity the field for the others fields.
     *
     * @param repeticoes number of occurrences
     * @param numLinhas number of line
     * @return the percentage of equals fields.
     */
    public float computePercentage(int repeticoes, int numLinhas) {

        return ((float) ((repeticoes * 100) / numLinhas) / 100);
    }

    /**
     * Para a correta utilização dessa classe é necessário implementar a
     * estrutura de extends da classe original para poder utilizar as funções de
     * sobreposição aos métodos e para que possamos executar de modo dinâmico as
     * expressões regulares. Construção da classe abstrata.
     *
     * @param field
     * @throws Exception
     */
    private void extractField(String field) throws Exception {

        for (int j = 0; j < list.size(); j++) {

            String nameExpression;

            Pattern patNumVal = Pattern.compile(list.get(j).getExpression());
            Matcher matNumVal = patNumVal.matcher(field);

            while (matNumVal.find()) {

                nameExpression = matNumVal.group();
                System.out.println("- " + nameExpression);
            }
        }
    }
}
