/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alg_listextract;

/**
 * This class recognizes the type 'Phone'.
 * 
 * @since 15/07/2012 - Last change: 11/09/2012
 * @version 0.1
 * @author Juliano R.
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class PhoneFieldExtractor extends FieldExtractor {

    /**
     * 
     * @return the extracted Phone
     */
    @Override
    public String getExpression() {

        return "(\\(?\\d{2}\\)?[\\s-]?\\d{4}-?\\d{4})+";

    }

    /**
     * 
     * @return the score of Phone
     */
    @Override
    public double getScore() {

        return 0.6;

    }

    @Override
    public String getNameExpression() {
        return "Phone_Expression";
    }
}
