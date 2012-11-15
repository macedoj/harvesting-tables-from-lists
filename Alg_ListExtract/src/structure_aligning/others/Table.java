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
public class Table {

    public Table() {
    }

    public void teste1() {

        double scoreA, scoreB, scoreC;
        scoreA = 2.7;
        scoreB = 5.0;
        scoreC = 7.8;
        ArrayList<Line> arrayColumns;

        Column objColumn = new Column();

        arrayColumns = objColumn.constructColumns(scoreA, scoreB, scoreC);

        for (int j = 0; j < arrayColumns.size(); j++) {
            System.out.println(arrayColumns.get(j).getScoreA());
            System.out.println(arrayColumns.get(j).getScoreB());
            System.out.println(arrayColumns.get(j).getScoreC());

        }

    }

    public void linhas() {
 
        Line objLine = new Line();
        Column objColumn = new Column();
        ArrayList<Line> arrayColumns = new ArrayList<>();;
        ArrayList<Line> arrayColumns2 = new ArrayList<>();;

        System.out.println(objColumn.getColumns());
        
        double scoreA, scoreB, scoreC;
        scoreA = 2.7;
        scoreB = 5.0;
        scoreC = 7.8;

        objLine.setScoreA(scoreA);
        objLine.setScoreB(scoreB);
        objLine.setScoreC(scoreC);

        arrayColumns.add(objLine);
        objColumn.setColumns(arrayColumns);

        arrayColumns2 = objColumn.getColumns();
        
        for (int j = 0; j < arrayColumns2.size(); j++) {
            System.out.println(arrayColumns2.get(j).getScoreA());
            System.out.println(arrayColumns2.get(j).getScoreB());
            System.out.println(arrayColumns2.get(j).getScoreC());

        }
    }
}
