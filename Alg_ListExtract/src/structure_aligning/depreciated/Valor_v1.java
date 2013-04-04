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
public class Valor_v1 {

    private String valor;
    private ArrayList tabela;
    Coluna_v1 objColuna;

    public Valor_v1() {

        tabela = new ArrayList();
    }

    /**
     * 
     * @param objColuna
     * @return 
     */
    public ArrayList obterColuna(Coluna_v1 objColuna) {
        this.objColuna = objColuna;

        return this.objColuna.obterColuna();
    }

    /**
     * 
     * @param objColuna 
     */
    public void construirColuna(Coluna_v1 objColuna) {
        this.objColuna = objColuna;
        tabela.add(this.objColuna.construirColuna(this.objColuna));
    }

    /**
     * 
     * @return tabela[]
     */
    public ArrayList exibirTabela() {

        return tabela;
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
}
