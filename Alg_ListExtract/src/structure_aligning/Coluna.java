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
public class Coluna {

    private String nome;
    private ArrayList<Double> scores = new ArrayList<Double>();

    public void addScore() throws Exception{
        
        scores.add(Score.getInstance().getScore());
    }

    public ArrayList getColuna() {
        return scores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
