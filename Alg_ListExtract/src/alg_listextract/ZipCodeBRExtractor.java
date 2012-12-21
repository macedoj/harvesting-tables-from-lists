/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alg_listextract;

/**
 * This class recognizes the type 'ZipCode-BR'.
 * 
 * @since 15/07/2012 - Last change: 11/09/2012
 * @version 0.1
 * @author Juliano R.
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 * 
 */
public class ZipCodeBRExtractor extends FieldExtractor {

    /**
     * 
     * @return the extracted ZipCode-BR
     */
    @Override
    public String getExpression() {
        return "([0-9]{2}[0-9]{3}-[0-9]{3})+";
    }
    
    /**
     * 
     * @return the score of ZipCode-BR
     */
    @Override
    public double getScore() {
        return 0.9;
    }

    /**
     * 
     * @return the name of expression
     */
    @Override
    public String getNameExpression() {
        return "ZiCodeBR_Expression";
    }
}
