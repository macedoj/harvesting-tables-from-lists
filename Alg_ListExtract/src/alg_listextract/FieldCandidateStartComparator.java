/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alg_listextract;

import java.util.Comparator;

/**
 * This class is comparator of start position of the selected candidate in the
 * line of ArrayList.
 *
 * @since 15/07/2012 - Last change: 29/09/2012
 * @version 0.1
 * @author Juliano R.
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 * Fonte de Pesquisa: http://docs.oracle.com/javase/1.4.2/docs/api/java/util/Comparator.html
 *
 */
public class FieldCandidateStartComparator implements Comparator<FieldCandidate> {

    /**
     * Um inteiro menor que zero se objeto atual for 'menor' que o recebido como
     * parâmetro.
     * Um inteiro maior que zero se objeto atual for 'maior' que o recebido como
     * parâmetro.
     * Zero se objetos forem iguais.
     *
     * @param fCandidate1
     * @param fCandidate2
     * @return the result of comparator [1, -1 or 0]
     */
    @Override
    public int compare(FieldCandidate fCandidate1, FieldCandidate fCandidate2) {

        if (fCandidate1.getStart() > fCandidate2.getStart()) {

            return 1;

        } else if (fCandidate1.getStart() < fCandidate2.getStart()) {

            return -1;

        } else {

            return 0;
        }
    }
}
