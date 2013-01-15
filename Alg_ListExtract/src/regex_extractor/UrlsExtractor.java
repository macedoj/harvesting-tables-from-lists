/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex_extractor;

import alg_listextract.FieldExtractor;

/**
 * This class recognizes the type 'Urls'.
 * 
 * @since 15/07/2012 - Last change: 11/09/2012
 * @version 0.1
 * @author Juliano R.
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 * 
 */
public class UrlsExtractor extends FieldExtractor {

    /**
     * 
     * @return the extracted Url
     */
    @Override
    public String getExpression() {
        return "(http[s]?://|ftp://)?(www\\.)?[a-zA-Z0-9-\\.]+\\.(com|org|net|mil|edu|ca|co.uk|com.au|gov|br)+";
    }

    /**
     * 
     * @return the score of Url
     */
    @Override
    public double getScore() {
        return 0.5;
    }

    /**
     * 
     * @return the name of expression
     */
    @Override
    public String getNameExpression() {
        return "Urls_Expression";
    }
    
    
    
}
