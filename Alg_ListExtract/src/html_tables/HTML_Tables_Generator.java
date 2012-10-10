/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package html_tables;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    
    public void addingLineInTable(ArrayList arrayLines){
               
        for(int j=0; j < arrayLines.size(); j++){
            
            line += "<td>" + arrayLines.get(j) + "</td>";
           // System.out.println("Teste: "+ (j) + " | " + arrayLines.get(j)); 
        }
        
        line += "</tr> </ br>";
      //  System.out.println(line);       
    }
    
    public void geraTabela(){
        
        String cabecalho = "<!DOCTYPE HTML PUBLIC "+"-//W3C//DTD HTML 4.01//EN"+ "http://www.w3.org/TR/html4/strict.dtd"+">" + "</ br>" 
                + "<html lang="+"pt-br"+">" + "</ br>" + "<head>" + "</ br>" + " <meta http-equiv="+"content-type"+" content="+"text/html; charset=utf-8"+">"
                + "</ br>" + "</head>" + "<body>" + "</ br>" + "<div id="+"tabela>"+"<table border="+"1"+">";
        String fim = "</table> </div> </body> </html>";
     
       newOutFile(nameFile, cabecalho, fim);
        
    }

    /**
     * Método responsável por criar um novo arquivo(arquivo de saída) para
     * armazenar os dados filtrados do arquivo original.
     * 
     * @param nameOutFile Nome do arquivo que esta sendo manipulado
     * @param cabecalho
     * @param fimPage 
     */
    public void newOutFile(String nameOutFile, String cabecalho, String fimPage) {

        nameOutFile = this.nameFile;
        String nameNewFile = "";

        try {

            // Cria um novo arquivo no diretório de saída especificado.
            File file = new File("files\\outLists", "TABELA_" + nameOutFile + "");
            file.createNewFile();

            // Recupera o nome do arquivo criado.
            nameNewFile = file.getName();

            System.out.println("\n Name out file: " + nameNewFile + "\n");

        } catch (IOException IOerror) {

            System.out.println("I/O ERROR: " + IOerror);

        } catch (Exception otherError) {

            System.out.println("Other Exception: " + otherError);

        }

        // Chama o método que escreve a estrutura já filtrada no arquivo txt de saída.
        writeOutFile(nameNewFile, cabecalho, fimPage);
    }

    /**
     * Método responsável por gravar os dados filtrados no arquivo de saída.
     * 
     * @param nameOutFile Nome do arquivo que esta sendo manipulado
     * @param cabecalho cabecalho html
     * @param fimPage fim da estruturação html da página
     */
    @SuppressWarnings({"ConvertToTryWithResources"})
    public void writeOutFile(String nameOutFile, String cabecalho, String fimPage) {

        try {

            FileWriter fstream = new FileWriter("files\\outLists\\" + nameOutFile + "");

            BufferedWriter out = new BufferedWriter(fstream);

            out.write(cabecalho);
            out.newLine();
            out.write(line);
            out.newLine();
            out.write(fimPage);

            // Closes the file.
            out.close();

        } catch (IOException IOerror) {

            System.out.println("I/O ERROR: " + IOerror);

        } catch (Exception otherError) {

            System.out.println("Other Exception: " + otherError);

        }
    }
    
    
}
