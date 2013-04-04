/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structure_aligning;

/**
 *
 * @author Juliano R. Macedo
 * @version 0.1
 * @since 15/11/2012 - Last change: 28/03/2013
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 */
public class Score {

    private static Score objScore = null;
    private double score;

    /**
     * Don't let anyone instantiate this class.
     */
    private Score() {
    }

    /**
     * This is Singleton method!
     *
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
