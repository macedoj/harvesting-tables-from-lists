/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alg_listextract;

/**
 * This class recognizes the type 'Currency Values'.
 *
 * @since 15/07/2012 - Last change: 11/09/2012
 * @version 0.1
 * @author Juliano R.
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class CurrencyValuesExtractor extends FieldExtractor {

    /**
     * 
     * @return the extracted Currency Value
     */
    @Override
    public String getExpression() {
        return "((?:[1-9](?:[\\d]{0,2}(?:\\.[\\d]{3})*|[\\d]+)|0)(?:,[\\d]{0,2})?)+";
    }

    /**
     * 
     * @return the score of Currency Value
     */
    @Override
    public double getScore() {
        return 0.4;
    }

    /**
     * 
     * @return the name of expression
     */
    @Override
    public String getNameExpression() {
        return "CurrencyValues_Expression";
    }
}
