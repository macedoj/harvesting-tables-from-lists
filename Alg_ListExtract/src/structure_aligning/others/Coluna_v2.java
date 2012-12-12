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
public class Coluna_v2 {

    private ArrayList score;
    String nome;


    public Coluna_v2() {

        score = new ArrayList();
    }

    /**
     * Verificar a necessidade deste método
     * 
     * @return o array da coluna contendo os scores
     */
    public Object obterEscore(int indice) {

        return getScore(indice);
    }

    /**
     * 
     * @param objColuna
     * @return 
     */
//    public ArrayList construirColuna() {
//
//        return constroiColuna();
//    }

    /**
     *
     * @param objColuna
     * @return 
     */
//    private ArrayList constroiColuna() {
//
//        Column.add(objColuna.getScore());
//        return Column;
//    }

    /**
     * @return the Score
     */
    public Object getScore(int indice) {
        return score.get(indice); 
    }

    /**
     * @param Score the Score to set
     */
    public void setScore(double Score) {
        this.score.add(Score);
    }

    public void addScore(double score) {
        setScore(score);
    }
 }
