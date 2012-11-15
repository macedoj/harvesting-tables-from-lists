/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structure_aligning.others;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Column {

    private ArrayList<Line> columns = new ArrayList<>();

    public Column() {
    }

    /**
     * 
     * @param scoreA
     * @param scoreB
     * @param scoreC
     * @return ArrayList<Line> columns
     */
    public ArrayList constructColumns(double scoreA, double scoreB, double scoreC) {

        Line objLines = new Line();
        ArrayList<Line> arrayColumns = new ArrayList<Line>();

        objLines.setScoreA(scoreA);
        objLines.setScoreB(scoreB);
        objLines.setScoreC(scoreC);

        arrayColumns.add(objLines);
        setColumns(arrayColumns);

        return getColumns();
    }

    /**
     * @return the columns
     */
    public ArrayList<Line> getColumns() {
        return columns;
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(ArrayList<Line> columns) {
        this.columns = columns;
    }
}
