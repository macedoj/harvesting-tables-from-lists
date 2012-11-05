/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package html_tables;

import file_handler.FileHandler;
import java.util.ArrayList;

/**
 * This class is generator html tables.
 *
 * @since 15/07/2012 - Last change: 09/10/2012
 * @version 0.1
 * @author Juliano R. Macedo
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class HTML_Tables_Generator {

    private String nameFile;
    private String row = "<tr>";

    /**
     * Constructor of this class
     *
     * @param numColumn
     * @param nameOfFile
     */
    public HTML_Tables_Generator(String nameOfFile) {

        this.nameFile = nameOfFile;
    }

    /**
     * Add the values in row, that is subsequently written to the file
     *
     * @param arrayLines
     */
    public void addingLineInTable(ArrayList arrayLines) {

        try {

            for (int j = 0; j < arrayLines.size(); j++) {

                row += "<td>" + arrayLines.get(j) + "</td>";
                // System.out.println("Teste: "+ (j) + " | " + arrayLines.get(j)); 
            }

            row += "</tr> \n";
            //  System.out.println(line);    
        } catch (Exception Error) {

            System.out.println("Exception: " + Error);
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
            String fim = "</table> </div> </body> </html>";


            FileHandler fileHandler = new FileHandler();
            fileHandler.newOutFile(nameFile, cabecalho, row, fim);

        } catch (Exception Error) {

            System.out.println("Exception: " + Error);
        }
    }
}
