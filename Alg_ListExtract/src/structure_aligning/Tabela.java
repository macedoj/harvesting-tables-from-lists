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
public class Tabela {

    ArrayList<Valor> valores = new ArrayList<Valor>();

    public void addValor(Valor v) {
        valores.add(v);
    }

    public void verTabela() {
        for (int j = 0; j < valores.size(); j++) {
            System.out.println(valores.get(j) + " | ");
        }
    }
}
