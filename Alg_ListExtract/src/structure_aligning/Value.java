/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structure_aligning;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliano R. Macedo
 * @version 0.1
 * @since 15/11/2012 - Last change: 28/03/2013
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 */
public class Value {

    private String value;
    private List lines; //Terá por exemplo 3 colunas.

    public Value() {
        lines = new ArrayList<Double>();
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return value;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.value = valor;
    }

    public List getLines() {
        return lines;
    }

    public void setLines(List lines) {
        this.lines = lines;
    }

    /**
     *
     * @param line
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public void addLine(Line line) {
        try {
            lines.add(line.getScores());
        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
    }
}
