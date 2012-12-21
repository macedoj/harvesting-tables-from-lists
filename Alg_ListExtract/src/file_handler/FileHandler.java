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
 * Classe responsável por realizar toda a manipulação de arquivos e diretórios.
 *
 * @since 10/05/2012 - Last change: 09/10/2012
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
            ListExtract listExtract = new ListExtract();
            File file = new File(DirectoryConfig.getInstance().getExtractDirectory());

            // Se o diretório existir entra no if. 
            if (file.exists() && file.isDirectory()) {

                // Coloca no array os nomes de todos os arquivos encontrados no diretório.
                String[] filesNames = file.list();

                // Retorna a quantidade de arquivos existentes no diretorio.
                int cout = file.listFiles().length;
                // poderia utilizar filesNames.length tambem.
                System.out.println(" Existem " + cout + " arquivos no diretório.");

                // Varrer todo o array.
                for (int j = 0; j < filesNames.length; j++) {

                    String fName = filesNames[j];
                    //System.out.println("File selected: " + fName);

                    @SuppressWarnings("UnusedAssignment")
                    ArrayList listIsEmpty = new ArrayList();
                    listIsEmpty = readingFiles(fName);

                    if (!listIsEmpty.isEmpty()) {

                        listExtract.split_Lines(fName, readingFiles(fName));
                    } else {
                        /**
                         * This file is empty.
                         */
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
     * Método responsável por exectar a chamada dos algoritmos para trabalhar
     * sobre o arquivo recebido por parâmetro.
     *
     * @param fileName
     */
    public void splitFile(String fileName) {

        try {
            ListExtract listExtract = new ListExtract();

            @SuppressWarnings("UnusedAssignment")
            ArrayList listRows = new ArrayList();
            listRows = readingFiles(fileName);

            if (!listRows.isEmpty()) {

                listExtract.split_Lines(fileName, readingFiles(fileName));
            } else {
                /**
                 * This file is empty.
                 *
                 * O arquivo aberto esta vazio, por isso não se faz nada.
                 */
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
     * @param nameFile Nome do arquivo a ser manipulado.
     */
    public ArrayList readingFiles(String nameFile) {

        ArrayList list = new ArrayList<>();
        boolean notEmpty;
        String lineFile;
        int numLine = 0;

        try {
            /**
             * Abre e carrega no buffer o arquivo cujo nome foi recebido por
             * parâmetro(nameFile).
             */
            BufferedReader readFile = new BufferedReader(new FileReader(DirectoryConfig.getInstance().getExtractDirectory() + nameFile));

            notEmpty = readFile.ready();
            /**
             * Check if the file is empty
             *
             * Verifica se existe conteúdo no arquivo informado.
             */
            if (notEmpty) {

                while (readFile.ready()) {

                    lineFile = readFile.readLine();
                    numLine++;
                    list.add(lineFile);
                }
                // Closes the file.
                readFile.close();

            } else {

                System.out.println(" The file selected is empty.");
            }

        } catch (IOException IOerror) {

            System.out.println("I/O ERROR: " + IOerror);

        } catch (Exception otherError) {

            System.out.println("Other Exception: " + otherError);
        }

        return list;
    }
//
//================================ Out Files =============================
//

    /**
     * Método responsável por criar um novo arquivo(arquivo de saída) para
     * armazenar os dados filtrados do arquivo original.
     *
     * @param nameOutFile Nome do arquivo que esta sendo manipulado
     * @param cabecalho cabecalho html
     * @param endPage fim da estruturação html da página
     */
    public void newOutFile(String nameOutFile, String cabecalho, String row, String endPage) { 

        String nameNewFile = "";

        try {

            // Cria um novo arquivo no diretório de saída especificado.
            File file = new File(DirectoryConfig.getInstance().getOutDirectory(), "TABELA_" + nameOutFile + ".html");
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
        writeOutFile(nameNewFile, cabecalho, row, endPage);
    }

    /**
     * Método responsável por gravar os dados filtrados no arquivo de saída.
     * @param nameOutFile Nome do arquivo que esta sendo manipulado
     * @param cabecalho cabecalho html
     * @param row Linha que contem os dados
     * @param fimPage fim da estruturação html da página  
     */
    @SuppressWarnings({"ConvertToTryWithResources"})
    public void writeOutFile(String nameOutFile, String cabecalho, String row, String fimPage) {

        try {

            FileWriter fstream = new FileWriter(DirectoryConfig.getInstance().getOutDirectory() + nameOutFile + "");

            BufferedWriter out = new BufferedWriter(fstream);

            out.write(cabecalho);
            out.newLine();
            out.write(row);
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
