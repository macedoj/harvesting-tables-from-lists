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
public class Valor_v2 {

    private String valor;
    private ArrayList<Coluna_v2> colunas;

    public Valor_v2() {
        colunas = new ArrayList<Coluna_v2>();
    }

    /**
     *
     * @param objColuna
     * @return
     */
    public Coluna_v2 obterColuna(int indice) {
        return this.colunas.get(indice);
    }

    public void adiconarColuna(double score) {

        Coluna_v2 coluna = new Coluna_v2();
//        coluna.addScore(score);
        //Verificar como fica o add aqui
        this.colunas.add(coluna);
    }

    public void addScoreIncolumn(int index, double score) {
        //Verificar como fica o add aqui
        this.colunas.get(index).addScore(score);
    }

 
    public void exibirTabela() {

        for (int j = 0; j < colunas.size(); j++) {

            System.out.print(colunas.get(j));
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
