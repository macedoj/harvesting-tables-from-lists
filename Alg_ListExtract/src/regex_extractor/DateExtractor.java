/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex_extractor;

import alg_listextract.FieldExtractor;

/**
 * This class recognizes the type 'Date'.
 * 
 * @since 15/07/2012 - Last change: 11/09/2012
 * @version 0.1
 * @author Juliano R.
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class DateExtractor extends FieldExtractor {

    /**
     * 
     * @return the extracted Date
     */
    @Override
    public String getExpression() {
      
        //return "^([1-9]|0[1-9]|[1,2][0-9]|3[0,1])/([1-9]|1[0,1,2])/\\d{4}$";
        //00/00/0000
        return "([0-9]{2}/[0-9]{2}/[0-9]{4})";
        
        /**
         * É NECESSÁRIO VALIDAR ESTAS EXPRESSÕES REGULARES!
         */
        
        //^\d{4}-(0[1-9]|1[0,1,2])-(0[1-9]|[1,2][0-9]|3[0,1])$
        
//        return "((((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])"
//                + "\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|"
//                + "((16|[2468][048]|[3579][26])00)))))+";
    }

    /**
     * 
     * @return the score of Date
     */
    @Override
    public double getScore() {
        return 0.7;
    }

    /**
     * 
     * @return the name of expression
     */
    @Override
    public String getNameExpression() {
        return "Date_Expression";
    }
}
