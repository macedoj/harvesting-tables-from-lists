/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package html_tables;

import file_handler.FileConfig;
import file_handler.FileHandler;
import java.util.ArrayList;

/**
 * This class is generator HTML tables.
 *
 * @since 15/09/2012 - Last change: 09/10/2012
 * @version 0.1
 * @author Juliano R. Macedo
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class HTML_TablesGenerator extends FileConfig {

    /**
     * Constructor of this class
     *
     * @param nameOfFile file name manipulated now
     */
    public HTML_TablesGenerator(String nameOfFile) {

        setNameFile(nameOfFile);
        String startRow = "";
        setRowFile(startRow);
    }

    /**
     * Add the values in row, that is subsequently written to the file
     *
     * @param arrayLines array contains lines of the file
     */
    public void addingLineInTable(ArrayList arrayLines) {

        try {
            String startRow = "<tr>";
            String endRow = "</tr> \n";
            for (int j = 0; j < arrayLines.size(); j++) {

                if (j == 0) {
                    setRowFile(getRowFile() + startRow);
                    constructionRow("<td>" + arrayLines.get(j) + "</td>");
                } else {
                    constructionRow("<td>" + arrayLines.get(j) + "</td>");
                }
            }
            setRowFile((getRowFile()) + endRow);
        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
            //System.out.println("Exception: " + error);
        }
    }

    /**
     * Construct the corps of table based in HTML
     *
     * Método responsável por criar a estrutura da tabela de acordo com a
     * linguagem de marcação HTML, trata-se de uma construção rustica de um
     * tabela HTML para esta receber os valores vindos da lista.
     */
    public void tableGenerator() {

        try {
            String cabecalho = "<!DOCTYPE HTML PUBLIC " + "-//W3C//DTD HTML 4.01//EN" + "http://www.w3.org/TR/html4/strict.dtd" + ">"
                    + "<html lang=" + "pt-br" + ">" + "<head>" + " <meta http-equiv=" + "content-type" + " content=" + "text/html; charset=utf-8" + ">"
                    + "</head>" + "<body>" + "<div id=" + "tabela>" + "<table border=" + "1" + ">";
            String lastRowFile = "</table> </div> </body> </html>";

            FileHandler fileHandler = new FileHandler();
            fileHandler.newOutFile(getNameFile(), cabecalho, getRowFile(), lastRowFile);

        } catch (Exception error) {
            /**
             * Show the StackTrace error [for debug]
             */
            error.printStackTrace();
            //System.out.println("Exception: " + error);
        }
    }
}
