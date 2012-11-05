/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alg_listextract;

/**
 * This class recognizes the type 'ZipCode-EUA'.
 * 
 * @since 15/07/2012 - Last change: 11/09/2012
 * @version 0.1
 * @author Juliano R.   
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 * 
 */
public class ZipCodeEUAExtractor extends FieldExtractor {

    /**
     * 
     * @return the extracted ZipCode-EUA
     */
    @Override
    public String getExpression() {
        return "(\\d{5}(-\\d{4})?)+";
    }

    /**
     * 
     * @return the score of ZipCode-EUA
     */
    @Override
    public double getScore() {
        return 0.75;
    }
}
