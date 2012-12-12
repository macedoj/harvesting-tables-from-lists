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
public class Valor {

    private String valor;
    private ArrayList<Coluna> colunas;

    public Valor() {
        colunas = new ArrayList<>();
    }

    /**
     *
     * @param objColuna
     * @return
     */
    public Coluna obterColuna(int indice) {
        return this.colunas.get(indice);
    }

    public void adiconarColuna(double score) {

        Coluna coluna = new Coluna();
        coluna.addScore(score);
        //Verificar como fica o add aqui
        this.colunas.add(coluna);
    }

    public void addScoreIncolumn(int index, double score) {
        //Verificar como fica o add aqui
        this.colunas.get(index).addScore(score);
    }

 
    public void exibirTabela() {

        for (int j = 0; j < colunas.size(); j++) {

            System.out.println(colunas.get(j));
        }
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     *
     * @return o tamanho do array
     */
    public int tamArrayColunas() {

        return colunas.size();
    }
}
