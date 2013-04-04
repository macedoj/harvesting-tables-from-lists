/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structure_aligning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Juliano R. Macedo
 * @version 0.1
 * @since 15/11/2012 - Last change: 28/03/2013
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 */
public class Table {

//    private ArrayList<Value> values;
    private HashMap<String, List> valuesScores;

    public Table() {
//        values = new ArrayList<Value>();
        valuesScores = new HashMap<String, List>();
    }

//    public ArrayList<Value> getValues() {
//        return values;
//    }
//
//    public void setValues(ArrayList<Value> values) {
//        this.values = values;
//    }
    public HashMap<String, List> getValuesScores() {
        return valuesScores;
    }

    public void setValuesScores(HashMap<String, List> valuesScores) {
        this.valuesScores = valuesScores;
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public void addValue(Value value) {
        try {
            valuesScores.put(value.getValor(), value.getLines());

        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
    }

    public void showTable() {
//        for (int j = 0; j < values.size(); j++) {
//            System.out.println(values.get(j) + " | ");
//        }
    }
}
