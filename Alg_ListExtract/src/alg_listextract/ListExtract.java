/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alg_listextract;

import html_tables.HTML_Tables_Generator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * This class is responsible for construction the engine of the extraction
 *
 * Algoritmo para separar as possíveis conlunas da tabla. Faz parte do conjunto
 * de algoritmos do artigo "Harvesting Relational Tables from Lists on the Web"
 *
 * Os algoritmos do ListExtract descritos aqui, estão a partir da página 04 do
 * artigo.
 *
 * @since 15/07/2012 - Last change: 29/09/2012
 * @version 0.1
 * @author Juliano R.
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class ListExtract {

     HTML_Tables_Generator htmltg;
    
    public ListExtract() {
//        listSubSequen = new ArrayList<>();
//
//        listSubSequen.add("1. What's Opera Doc (Waner Bros/1975)");
//        listSubSequen.add("2. Duck Amuck (Waner Bros/1953)");
//        listSubSequen.add("3. The Band Concert (Disney/1935)");
//        listSubSequen.add("4. Duck Dodgers in the 24 1/2th Century (Warnner Bross/1953)");
//        listSubSequen.add("5. One Froggy Evening (Waner Bros/1956)");
//        listSubSequen.add("6. Gertie The Dinossaur (McCay)");
//        listSubSequen.add("7. Red Hot Riding Hood (MGM/1943");
//        listSubSequen.add("8. Porky In Wackyland (Waner Bros/1938)");
//        listSubSequen.add("9. Gerald McBoing Boing (UPA/1951)");
//        listSubSequen.add("10. King-Size Canary (MGM/1947)");
//        listSubSequen.add("11. Three Little Pigs (Disney/1933)");
//        listSubSequen.add("12. Rabbit of Seville (Waner Bros/1950)");
//        listSubSequen.add("13. Steamboat Willie (Disney/1928)");
//        listSubSequen.add("14. The Old Will (Disney/1937)");
//        listSubSequen.add("15. Bad Luck Blackie (MGM/1949)");
//        listSubSequen.add("16. The Great Piggy Bank Robbery (Waner Bros/1946)");
//        listSubSequen.add("17. Popeye the Sailor Meets Sindbad the Sailor (Fleischer/1936)");
    }

    /**
     *
     * We create Cf , a ranked list of all field candidates sorted in descending
     * order of their FQ scores. In each iteration of the loop, the candidate
     * with the highest score, ftop, is removed from the ranked list and marked
     * as selected (added to the output set r). All candidates that overlap with
     * ftop are then removed from Cf to ensure that no two overlapping fields
     * are selected. This process terminates when Cf becomes empty.
     *
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public void split_Lines(String nameFile, ArrayList listSubSequen) {


        try {

            ArrayList numColum;
            numColum = new ArrayList();

            ArrayList<ArrayList<FieldCandidate>> rowsFQS = new ArrayList<>();

            ArrayList<ArrayList<FieldCandidate>> rowsCadidates = new ArrayList<>();

            for (int j = 0; j < listSubSequen.size(); j++) {
                ArrayList<FieldCandidate> possibleFields = computeFQS(String.valueOf(listSubSequen.get(j)));
                rowsFQS.add(possibleFields);
            }

            for (int j = 0; j < rowsFQS.size(); j++) {

                ArrayList<FieldCandidate> selectedCandidates = split_line(rowsFQS.get(j), 0, Integer.MAX_VALUE);
                rowsCadidates.add(selectedCandidates);
                numColum.add(selectedCandidates.size());
            }

            int idealNumColumns = computeIdealNumColumns(numColum);

            System.out.println("Ideal Columns " + idealNumColumns);

            htmltg = new HTML_Tables_Generator(idealNumColumns, nameFile);

            for (int j = 0; j < rowsCadidates.size(); j++) {

                System.out.println("**********************************************************");
                ArrayList<FieldCandidate> selectedCandidates = rowsCadidates.get(j);
                //     System.out.println("Num Candidatos: " + selectedCandidates.size());

                if (selectedCandidates.size() > idealNumColumns) {

                    System.out.println(">>> Linha recalculada <<<");
                    ArrayList<FieldCandidate> newSelectedCandidates = split_line(rowsFQS.get(j), 0, idealNumColumns);
                    rowsCadidates.set(j, newSelectedCandidates);
                    outputCandidates(newSelectedCandidates);

                } else {

                    outputCandidates(selectedCandidates);
                }
            }

            htmltg.geraTabela();
        } catch (Exception error) {

            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
    }

    /**
     * For greater organization.
     *
     * Method responsible for compute the FQS.
     *
     * @param line
     * @return a list with fields candidates of the line
     */
    private ArrayList<FieldCandidate> computeFQS(String line) {

        ArrayList<FieldCandidate> listCandFields = fieldQualityScore(line);
        Collections.sort(listCandFields);
        Collections.reverse(listCandFields);

        return listCandFields;
    }

    /**
     *
     * @param candidates
     * @param start
     * @param maxFields
     * @return a list with selected candidates
     */
    @SuppressWarnings("CallToThreadDumpStack")
    private ArrayList<FieldCandidate> split_line(ArrayList<FieldCandidate> candidates, int start, int maxFields) {

        ArrayList<FieldCandidate> selectedCandidates = new ArrayList<>();

        try {
            for (int posList = start; posList < candidates.size(); posList++) {

                FieldCandidate candidate = candidates.get(posList);
                //System.out.println("Ordenado: " + listCandFields.get(posList));

                boolean mustAdd = true;

                for (int i = 0; i < selectedCandidates.size(); i++) {

                    FieldCandidate selectedCandidate = selectedCandidates.get(i);

                    if (hasIntersection(candidate, selectedCandidate)) {

                        mustAdd = false;
                        break;
                    }
                }

                if (mustAdd) {
                    if (maxFields != Integer.MAX_VALUE) {

                        ArrayList<FieldCandidate> selectedCandidates2 = split_line(candidates, posList + 1, Integer.MAX_VALUE);

                        if (selectedCandidates.size() + selectedCandidates2.size() < maxFields) {

                            selectedCandidates.add(candidates.get(posList));
                        }
                    } else {

                        selectedCandidates.add(candidates.get(posList));
                    }
                }
            }
            //Call the Garbage Collector
            System.gc();

        } catch (Exception error) {

            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }

        return selectedCandidates;
    }

    /**
     * Show selected candidates
     *
     * @param selectedCandidates
     */
    @SuppressWarnings("CallToThreadDumpStack")
    private void outputCandidates(ArrayList<FieldCandidate> selectedCandidates) {

        try {

            ArrayList arrayTeste = new ArrayList<>();

            FieldCandidateStartComparator sPosition = new FieldCandidateStartComparator();

            Collections.sort(selectedCandidates, sPosition);

            for (int x = 0; x < selectedCandidates.size(); x++) {

                arrayTeste.add(selectedCandidates.get(x).getField());
                System.out.print(selectedCandidates.get(x).getField() + " | ");
            }

            System.out.println("");

            htmltg.addingLineInTable(arrayTeste);
            arrayTeste.clear();

        } catch (Exception error) {

            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
    }

    /**
     *
     * We now describe how we calculate the field-quality score, FQ(f), for a
     * given field candidate f.
     *
     * Type Score (Sts): The type score reflects whether the field candidate can
     * be recognized as a member of a type that commonly occurs in separate
     * columns in tables. Our implementation currently recognizes numeric
     * values, datetime values, currency values, URLs, emails, phone numbers,
     * and zip codes. Type recognition is performed by matching f against
     * regular expressions, which capture most of the possible instances of the
     * considered types.
     *
     * @param subSequences
     * @return the list with candidates found
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public ArrayList<FieldCandidate> fieldQualityScore(String subSequences) {

        ArrayList<FieldCandidate> candidatesFound = new ArrayList<FieldCandidate>();

        try {

            candidatesFound.addAll(new CurrencyValuesExtractor().extractFields(subSequences));
            candidatesFound.addAll(new EmailExtractor().extractFields(subSequences));
            candidatesFound.addAll(new PhoneFieldExtractor().extractFields(subSequences));
            candidatesFound.addAll(new TextExtractor().extractFields(subSequences));
            candidatesFound.addAll(new TimeExtractor().extractFields(subSequences));
            candidatesFound.addAll(new UrlsExtractor().extractFields(subSequences));
            candidatesFound.addAll(new ZipCodeBREstractor().extractFields(subSequences));
            candidatesFound.addAll(new ZipCodeEUAExtractor().extractFields(subSequences));

        } catch (Exception error) {

            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
        return candidatesFound;
    }

    /**
     *
     * @param candidate
     * @param selectedCandidate
     * @return
     */
    private boolean hasIntersection(FieldCandidate candidate, FieldCandidate selectedCandidate) {

        return (hasIntersection(candidate.getEnd(), selectedCandidate)
                || hasIntersection(selectedCandidate.getEnd(), candidate));
    }

    /**
     *
     * @param pos
     * @param selectedCandidate
     * @return
     */
    private boolean hasIntersection(int pos, FieldCandidate selectedCandidate) {

        return ((pos >= selectedCandidate.getStart()) && (pos <= selectedCandidate.getEnd()));
    }

    /**
     * Since the lines were split independently, it is possible the resulting
     * records have different numbers of fields. We use a simple majority voting
     * scheme to determine the number of columns – suppose the first phase split
     * the row Ri into Ki fields. We pick the Ki that occurs the most number of
     * times in the list.
     *
     * @param arrayNcolum
     * @return the ideal number of columns
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public int computeIdealNumColumns(ArrayList arrayNcolum) {

        int idealNumColumns = 0;

        try {

            HashMap<Integer, Integer> mapColumnOccurrence = new HashMap<>();
            ArrayList<Integer> listNumColumns = arrayNcolum;
//            ArrayList arrayLongRecords = new ArrayList();

//        System.out.println("Lista de Colunas: " + listNumColumns);

            for (Integer integer : listNumColumns) {

                if (mapColumnOccurrence.containsKey(integer)) {

                    mapColumnOccurrence.put(integer, mapColumnOccurrence.get(integer) + 1);

                } else {
                    mapColumnOccurrence.put(integer, 1);
                }
            }
//        System.out.println("HashMap par Key(numColum) >> Value(numOcorrências): " + mapColumOccurren);

            Collection<Integer> valuesOfHMap;

            valuesOfHMap = mapColumnOccurrence.values();
            Integer highOccurrences = Collections.max(valuesOfHMap);

//        System.out.println("Value: " + highOccurrences);

            for (Entry<Integer, Integer> entry : mapColumnOccurrence.entrySet()) {
                if (highOccurrences.equals(entry.getValue())) {

                    idealNumColumns = entry.getKey();
//                System.out.println("Key: " + entry.getKey());
                }
            }
//
//            for (int j = 0; j < listNumColumns.size(); j++) {
//
//                if (listNumColumns.get(j) > idealNumColumns) {
//
//                    arrayLongRecords.add(String.valueOf(listSubSequen.get(j)));
//                }
//            }

        } catch (Exception error) {

            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }

        return idealNumColumns;
    }
}
