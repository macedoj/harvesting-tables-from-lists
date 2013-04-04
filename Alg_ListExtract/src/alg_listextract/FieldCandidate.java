/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alg_listextract;

/**
 * This class handles the possible field candidates
 *
 * @since 15/07/2012 - Last change: 29/09/2012
 * @version 0.1
 * @author Juliano R.
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 */
public class FieldCandidate implements Comparable<FieldCandidate> {

    private String field;
    private double score;
    private int start; //Posição inicial que o campo do grupo candidato inicia
    private int end; // Posição final que o campo do grupo candidato
    
    //Expressão correspondente
    private String expressCorrespon;

    /**
     * 
     * @return expressCorrespon
     */
    public String getExpressCorrespon() {
        return expressCorrespon;
    }

    /**
     * 
     * @param expressCorrespon 
     */
    public void setExpressCorrespon(String expressCorrespon) {
        this.expressCorrespon = expressCorrespon;
    }

    /**
     *
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     *
     * @param field
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     *
     * @return the score
     */
    public double getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {

        return getField() + " - " + getScore() + " - " + getStart() + " - " + getEnd();
    }

    /**
     *
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     *
     * @param start
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     *
     * @return the end
     */
    public int getEnd() {
        return end;
    }

    /**
     *
     * @param end
     */
    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public int compareTo(FieldCandidate fCand) {

        if (this.getScore() > fCand.getScore()) {

            return 1;
        } else if (this.getScore() < fCand.getScore()) {

            return -1;
        } else {

            return 0;
        }
    }
}
