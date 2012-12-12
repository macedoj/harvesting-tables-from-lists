/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structure_aligning;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Score {

    private double score;
    private ArrayList arrayScores;

    public Score() {

        arrayScores = new ArrayList();
    }
    
    /**
     * 
     * @param score 
     */
    public void addScoreNoArray(double score) {
        arrayScores.add(score);

    }
    /**
     * 
     * @return arrayScores
     */
    public ArrayList<Score> obterArrayScores() {

        return arrayScores;
    }

    /**
     * @return the score
     */
    public double getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(double score) {
        this.score = score;
    }
}
