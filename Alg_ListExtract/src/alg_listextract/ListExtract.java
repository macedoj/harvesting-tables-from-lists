/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alg_listextract;

import html_tables.HTML_TablesGenerator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import structure_aligning.AlignShortRecord;

/**
 * This class is responsible for construction the engine of the extraction
 *
 * @since 15/07/2012 - Last change: 29/09/2012
 * @version 0.1
 * @author Juliano R.
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class ListExtract {

    HTML_TablesGenerator htmltg;

    public ListExtract() {
    }

    /**
     * We create Cf , a ranked list of all field candidates sorted in descending
     * order of their FQ scores. In each iteration of the loop, the candidate
     * with the highest score, ftop, is removed from the ranked list and marked
     * as selected (added to the output set r). All candidates that overlap with
     * ftop are then removed from Cf to ensure that no two overlapping fields
     * are selected. This process terminates when Cf becomes empty.
     *
     * @param nameFile
     * @param listSubSequen
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
            htmltg = new HTML_TablesGenerator(nameFile);

            for (int j = 0; j < rowsCadidates.size(); j++) {

                System.out.println("**********************************************************");
                ArrayList<FieldCandidate> selectedCandidates = rowsCadidates.get(j);

                if (selectedCandidates.size() > idealNumColumns) {

                    System.out.println(">>> Linha recalculada <<<");
                    ArrayList<FieldCandidate> newSelectedCandidates = split_line(rowsFQS.get(j), 0, idealNumColumns);
                    rowsCadidates.set(j, newSelectedCandidates);
                    outputCandidates(newSelectedCandidates);

                } else if (selectedCandidates.size() < idealNumColumns) {

                    for (int i = 0; i < rowsCadidates.get(j).size(); i++) {

                        AlignShortRecord shortRecord = new AlignShortRecord();
                        shortRecord.pre_AlignShortRecord(selectedCandidates.get(i).getField(), rowsCadidates, idealNumColumns);
                    }
                    outputCandidates(selectedCandidates);
                } else {

                    outputCandidates(selectedCandidates);
                }
            }

            htmltg.tableGenerator();
        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
    }

    /**
     * For greater organization. Method responsible for compute the FQS.
     *
     * @param line
     * @return a list with fields candidates of the line
     */
    @SuppressWarnings("CallToThreadDumpStack")
    private ArrayList<FieldCandidate> computeFQS(String line) {

        ArrayList<FieldCandidate> listCandFields = new ArrayList<>();
        try {

            listCandFields = fieldQualityScore(line);
            Collections.sort(listCandFields);
            Collections.reverse(listCandFields);

        } catch (Exception error) {

            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
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
            /**
             * Show the StackTrace error [for debug]
             */
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
            ArrayList arrayRow = new ArrayList<>();
            FieldCandidateStartComparator sPosition = new FieldCandidateStartComparator();
            Collections.sort(selectedCandidates, sPosition);

            for (int x = 0; x < selectedCandidates.size(); x++) {

                arrayRow.add(selectedCandidates.get(x).getField());
                System.out.print(selectedCandidates.get(x).getField() + " | ");
            }
            System.out.println("");

            htmltg.addingLineInTable(arrayRow);
            arrayRow.clear();

        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
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
            candidatesFound.addAll(new DateExtractor().extractFields(subSequences));
            candidatesFound.addAll(new EmailExtractor().extractFields(subSequences));
            candidatesFound.addAll(new PhoneFieldExtractor().extractFields(subSequences));
            candidatesFound.addAll(new TextExtractor().extractFields(subSequences));
            candidatesFound.addAll(new TimeExtractor().extractFields(subSequences));
            candidatesFound.addAll(new UrlsExtractor().extractFields(subSequences));
            candidatesFound.addAll(new ZipCodeBRExtractor().extractFields(subSequences));
            candidatesFound.addAll(new ZipCodeEUAExtractor().extractFields(subSequences));

        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
        return candidatesFound;
    }

    /**
     * Find intersection between candidates.
     *
     * @param candidate
     * @param selectedCandidate
     * @return 'true' if found intersection between candidates or 'false' case not.
     */
    private boolean hasIntersection(FieldCandidate candidate, FieldCandidate selectedCandidate) {

        return (hasIntersection(candidate.getEnd(), selectedCandidate)
                || hasIntersection(selectedCandidate.getEnd(), candidate));
    }

    /**
     * Find intersection between candidates.
     *
     * @param pos
     * @param selectedCandidate
     * @return 'true' if found intersection between candidates or 'false' case not.
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
            if (!arrayNcolum.isEmpty()) {

                HashMap<Integer, Integer> mapColumnOccurrence = new HashMap<>();
                ArrayList<Integer> listNumColumns = arrayNcolum;

                for (Integer integer : listNumColumns) {

                    if (mapColumnOccurrence.containsKey(integer)) {

                        mapColumnOccurrence.put(integer, mapColumnOccurrence.get(integer) + 1);
                    } else {
                        mapColumnOccurrence.put(integer, 1);
                    }
                }

                Collection<Integer> valuesOfHMap;
                valuesOfHMap = mapColumnOccurrence.values();
                Integer highOccurrences = Collections.max(valuesOfHMap);

                for (Entry<Integer, Integer> entry : mapColumnOccurrence.entrySet()) {
                    if (highOccurrences.equals(entry.getValue())) {

                        idealNumColumns = entry.getKey();
                    }
                }
            }
        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
//            System.out.println("Exception: " + error);
        }
        return idealNumColumns;
    }
}
