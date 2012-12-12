/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structure_aligning;

/**
 *
 * @author PC
 */
public class Valor {

    private String valor;

    // public void defineScoreDoValor(double score1, double score2, double score3, int numColuna, Coluna c) {
    public void defineScoreDoValor(double score, Score s, Coluna c) {

        s.setScore(score);
        s.addScoreNoArray(s.getScore());

        addScoreNaColuna(c, s);
    }

    private void addScoreNaColuna(Coluna c, Score s) {

        c.addColuna(s);
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
