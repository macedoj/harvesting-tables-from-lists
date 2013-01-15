/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alg_listextract;

/**
 * This class recognizes the type 'Text'.
 * 
 * @since 15/07/2012 - Last change: 11/09/2012
 * @version 0.1
 * @author Juliano R.
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 * 
 */
public class TextExtractor extends FieldExtractor {

    /**
     * 
     * @return the extracted text
     */
    @Override
    public String getExpression() {
        return "(\\b(.?[a-zA-Z?])+)\\n?";
    }

    /**
     * 
     * @return the score of text
     */
    @Override
    public double getScore() {
        return 0.1;
    }

    /**
     * 
     * @return the name of expression
     */
    @Override
    public String getNameExpression() {
        return "Text_Expression";
    }
}
