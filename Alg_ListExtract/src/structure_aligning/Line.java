/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structure_aligning;

import java.util.ArrayList;

/**
 *
 * @author Juliano R. Macedo
 * @version 0.1
 * @since 15/11/2012 - Last change: 28/03/2013
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 */
public class Line {

    private String name;
    private ArrayList<Double> scores;//Terá por exemplos 3 Scores.

    public Line() {
        scores = new ArrayList<Double>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Double> getScores() {
        return scores;
    }

    /**
     *
     * @param scores the scores to set
     */
    public void setScores(ArrayList<Double> scores) {
        this.scores = scores;
    }

    /**
     * 
     * @param score the object the class Score.
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public void addScore(Score score) {
        try {
            scores.add(score.getScore());
        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug].
             */
            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
    }
}
