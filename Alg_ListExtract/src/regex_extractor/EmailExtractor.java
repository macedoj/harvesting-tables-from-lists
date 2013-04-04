/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex_extractor;

import alg_listextract.FieldExtractor;

/**
 * This class recognizes the type 'Email'.
 *
 * @since 15/07/2012 - Last change: 11/09/2012
 * @version 0.1
 * @author Juliano R.
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 */
public class EmailExtractor extends FieldExtractor {

    /**
     *
     * @return the extracted Email
     */
    @Override
    public String getExpression() {
        return "(\\b[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,3})+";
    }

    /**
     *
     * @return the score of Email
     */
    @Override
    public double getScore() {
        return 0.85;
    }

    /**
     *
     * @return the name of expression
     */
    @Override
    public String getNameExpression() {
        return "Email_Expression";
    }
}
