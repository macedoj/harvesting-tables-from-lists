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

    private ArrayList<ArrayList<Score>> coluna;

    public void criaColuna() {

        coluna = new ArrayList<>();
    }
    
    /**
     * 
     * @param numColuna the number of column
     * @param s the object of the class Score
     */
    public void addColuna(Score s) {
        //Verificar a necessidade de passar  o número da coluna para a insersão.
        coluna.add(s.obterArrayScores());
    }

    /**
     *
     * @param index of the column
     * @return the column
     */
    public ArrayList obterColuna(int index) {

        return coluna.get(index);
    }

    public void exibirTabela() {

        for (int j = 0; j < coluna.size(); j++) {

            System.out.println(coluna.get(j).get(j));
        }
    }

    /**
     *
     * @param index of the column
     * @return the size of column
     */
    public int tamColuna(int index) {

        return coluna.get(index).size();

    }
}
