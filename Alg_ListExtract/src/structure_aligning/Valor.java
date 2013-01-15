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

    ArrayList<Coluna> colunas = new ArrayList<Coluna>();
    
    /**
     * 
     * @param c 
     */
    public void addColuna(Coluna c)
    {
        colunas.add(c);
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
