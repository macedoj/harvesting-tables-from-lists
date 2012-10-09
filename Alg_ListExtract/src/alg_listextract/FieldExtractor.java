/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alg_listextract;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is a 'field extractor' of the records[lines]
 * 
 * @since 15/07/2012 - Last change: 29/09/2012
 * @version 0.1
 * @author Juliano R.
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 */
public abstract class FieldExtractor {

    /**
     * 
     * @param strSubSequences
     * @return fields found at the line
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public ArrayList<FieldCandidate> extractFields(String strSubSequences) {

        ArrayList<FieldCandidate> fieldsFound = new ArrayList<>();

        try {

            Pattern pNumVal = Pattern.compile(getExpression());
            Matcher mNumVal = pNumVal.matcher(strSubSequences);

            while (mNumVal.find()) {

                FieldCandidate fieldCand = new FieldCandidate();
//                String aux = mNumVal.group();
//                System.out.println(aux + " - " + getScore());  
                fieldCand.setField(mNumVal.group());
                fieldCand.setScore(getScore());

                fieldCand.setStart(mNumVal.start());
                fieldCand.setEnd(mNumVal.end());

                fieldsFound.add(fieldCand);
            }

        } catch (Exception error) {

            error.printStackTrace();
            // System.out.println("ERROR: " + error);
        }

        return fieldsFound;
    }

    /**
     *
     * @return the expression
     */
    public abstract String getExpression();

    /**
     *
     * @return the score
     */
    public abstract double getScore();
}
