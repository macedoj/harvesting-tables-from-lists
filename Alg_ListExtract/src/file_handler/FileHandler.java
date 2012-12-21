/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file_handler;

import alg_listextract.ListExtract;
import java.io.*;
import java.util.ArrayList;

/**
 * This class is responsible for handle all the files in the project
 *
 * @since 10/05/2012 - Last change: 21/12/2012
 * @version 0.2
 * @author Juliano R. Macedo
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 *
 */
public class FileHandler {

    public FileHandler() {
    }

    /**
     * Método responsável por listar e armazenar em um array de string os nomes
     * de todos os arquivos dentro do diretório previamente fornecido.
     */
    public void listFileInDirectory() {

        try {
            File file = new File(DirectoryConfig.getInstance().getExtractDirectory());

            if (file.exists() && file.isDirectory()) {
                String[] filesNames = file.list();

                for (int j = 0; j < filesNames.length; j++) {
                    String fName = filesNames[j];
                    
                    if (!fName.isEmpty()) {

                        splitFile(fName);
                    } else {
                        System.out.println("The file '" + fName + "' is empty!");
                    }
                    //Call the Garbage Collector
                    System.gc();
                }
            } else {
                System.out.println(" Error: Directory doesn't exists!");
            }
        } catch (Exception error) {

            System.out.println("Other Exception: " + error);
        }
    }

    /**
     * Método responsável por executar a chamada dos algoritmos para trabalhar
     * sobre o arquivo recebido por parâmetro.
     *
     * @param fileName
     */
    public void splitFile(String fileName) {

        try {
            ListExtract listExtract = new ListExtract();
            
            if (!fileName.isEmpty()) {

                listExtract.split_Lines(fileName, readingFiles(fileName));
            } else {
                System.out.println("The file '" + fileName + "' is empty!");
            }
            //Call the Garbage Collector
            System.gc();
        } catch (Exception error) {

            System.out.println("Other Exception: " + error);
        }
    }

    /**
     * Método responsável por ler um arquivo, armazenar seus dados no buffer e
     * chamar os métodos que filtram os dados, cria um novo arquivo de saída e
     * salvas os dados filtrados nele.
     *
     * @param nameFile
     * @return listRowsFile the list contains rows of the file.
     */
    private ArrayList readingFiles(String nameFile) {

        ArrayList listRowsFile = new ArrayList<>();
        boolean notEmpty;
        String lineFile;
        int lineNumb = 0;

        try {
            BufferedReader readFile = new BufferedReader(new FileReader(DirectoryConfig.getInstance().getExtractDirectory() + nameFile));
            notEmpty = readFile.ready();
            if (notEmpty) {
                while (readFile.ready()) {

                    lineFile = readFile.readLine();
                    lineNumb++;
                    listRowsFile.add(lineFile);
                }
                readFile.close();
            } else {

                System.out.println(" The file selected is empty.");
            }
        } catch (IOException IOerror) {

            System.out.println("I/O ERROR: " + IOerror);
        } catch (Exception otherError) {

            System.out.println("Other Exception: " + otherError);
        }
        return listRowsFile;
    }
//
//================================ Methods for 'Out File' =============================
//
    /**
     * Método responsável por criar um novo arquivo(arquivo de saída) para
     * armazenar os dados filtrados do arquivo original.
     *
     * @param nameOutFile Nome do arquivo que esta sendo manipulado
     * @param cabecalho cabecalho html
     * @param row Linha que contem os dados
     * @param endPage fim da estruturação html da página
     */
    public void newOutFile(String nameOutFile, String cabecalho, String row, String endPage) {

        String nameNewFile = "";
        try {
            if (!row.isEmpty()) {

                File file = new File(DirectoryConfig.getInstance().getOutDirectory(), "TABELA_" + nameOutFile + ".html");
                file.createNewFile();
                nameNewFile = file.getName();

                System.out.println("\n Name out file: " + nameNewFile + "\n");
            }
        } catch (IOException IOerror) {

            System.out.println("I/O ERROR: " + IOerror);
        } catch (Exception otherError) {

            System.out.println("Other Exception: " + otherError);
        }
        // Chama o método que escreve a estrutura já filtrada no arquivo txt de saída.
        writeOutFile(nameNewFile, cabecalho, row, endPage);
    }

    /**
     * Método responsável por gravar os dados filtrados no arquivo de saída.
     *
     * @param nameOutFile Nome do arquivo que esta sendo manipulado
     * @param cabecalho cabecalho html
     * @param row Linha que contem os dados
     * @param fimPage fim da estruturação html da página
     */
    @SuppressWarnings({"ConvertToTryWithResources"})
    public void writeOutFile(String nameOutFile, String cabecalho, String row, String fimPage) {

        try {
            if (!row.isEmpty()) {
                FileWriter fstream = new FileWriter(DirectoryConfig.getInstance().getOutDirectory() + nameOutFile);
                BufferedWriter out = new BufferedWriter(fstream);

                out.write(cabecalho);
                out.newLine();
                out.write(row);
                out.newLine();
                out.write(fimPage);
                out.close();
            }
        } catch (IOException IOerror) {

            System.out.println("I/O ERROR: " + IOerror);
        } catch (Exception otherError) {

            System.out.println("Other Exception: " + otherError);
        }
    }
}
