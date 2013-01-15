/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structure_aligning;

/**
 *
 * @author PC
 */
public class Score {

    private static Score objScore;
    private double score;

    public Score() {
    }

    /**
     * @see This is Singleton method!
     * @return the instance of this class
     */
    public static Score getInstance() {

        if (objScore == null) {
            
            objScore = new Score();
        }
        return objScore;
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
