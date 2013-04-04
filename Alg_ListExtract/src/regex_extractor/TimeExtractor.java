/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex_extractor;

import alg_listextract.FieldExtractor;

/**
 * This class recognizes the type 'Time'.
 *
 * @since 15/07/2012 - Last change: 11/09/2012
 * @version 0.1
 * @author Juliano R.
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class TimeExtractor extends FieldExtractor {

    /**
     * Find the pattern: 00:00
     * @return the extracted Time
     */
    @Override
    public String getExpression() {
        return "(([0-1][0-9]|[2][0-3])(:([0-5][0-9])){1,2})+";
    }

    /**
     *
     * @return the score of Time
     */
    @Override
    public double getScore() {
        return 0.65;
    }

    /**
     *
     * @return the name of expression
     */
    @Override
    public String getNameExpression() {
        return "Time_Expression";
    }
}
