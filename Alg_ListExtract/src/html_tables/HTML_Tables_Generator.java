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
    private String line = "<tr>";

    public HTML_Tables_Generator(int numColumn, String nameOfFile) {

        this.nameFile = nameOfFile;
    }

    public void addingLineInTable(ArrayList arrayLines) {

        for (int j = 0; j < arrayLines.size(); j++) {

            line += "<td>" + arrayLines.get(j) + "</td>";
            // System.out.println("Teste: "+ (j) + " | " + arrayLines.get(j)); 
        }

        line += "</tr> \n";
        //  System.out.println(line);       
    }

    public void geraTabela() {

        String cabecalho = "<!DOCTYPE HTML PUBLIC " + "-//W3C//DTD HTML 4.01//EN" + "http://www.w3.org/TR/html4/strict.dtd" + ">"
                + "<html lang=" + "pt-br" + ">" + "<head>" + " <meta http-equiv=" + "content-type" + " content=" + "text/html; charset=utf-8" + ">"
                + "</head>" + "<body>" + "<div id=" + "tabela>" + "<table border=" + "1" + ">";
        String fim = "</table> </div> </body> </html>";

        try {

            FileHandler fileHandler = new FileHandler();
            fileHandler.newOutFile(nameFile, cabecalho, line, fim);

        } catch (Exception Error) {

            System.out.println("Exception: " + Error);
        }
    }
}
