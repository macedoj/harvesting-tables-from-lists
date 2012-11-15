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
public class Coluna_v1 {

    private ArrayList Score;
    private ArrayList Column;

    public Coluna_v1() {

        Score = new ArrayList();
        Column = new ArrayList();
    }

    /**
     *
     * @return o array da coluna contendo os scores
     */
    public ArrayList obterColuna() {

        return Column;
    }

    /**
     * 
     * @param objColuna
     * @return 
     */
    public ArrayList construirColuna(Coluna_v1 objColuna) {

        return constroiColuna(objColuna);
    }

    /**
     *
     * @param objColuna
     * @return 
     */
    private ArrayList constroiColuna(Coluna_v1 objColuna) {

        Column.add(objColuna.getScore());
        return Column;
    }

    /**
     * @return the Score
     */
    public ArrayList getScore() {
        return Score;
    }

    /**
     * @param Score the Score to set
     */
    public void setScore(double Score) {
        this.Score.add(Score);
    }
}
