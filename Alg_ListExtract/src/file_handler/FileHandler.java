/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file_handler;

import java.io.*;

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

    // Variável que recebe a estrutura de cada linha do arquivo
    private String charStructure = "";
    // Variável que recebe o valor do score de estruturação
    private String scoreStructure = "";
    // Variável que define o diretório.
    String directory = "files\\lists\\";

    public FileHandler() {

//        patternFinder = new BetterAndTolerantPatternFinder(token);
    }

    /**
     * Método responsável por listar e armazenar em um array de string os nomes
     * de todos os arquivos dentro do diretório previamente fornecido.
     *
     */
    public void listFileInDirectory() {

        try {

            File file = new File(directory);

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

                    // Chama o método que irá abrir cada um dos arquivos do diretório(de acordo com a execução do for).
                    readingFiles(fName);
                }

            } else {
                //Informa que o diretório especificado não existe.
                System.out.println("Directory does not exist!");
            }

        } catch (Exception otherError) {

            System.out.println("Other Exception: " + otherError);
        }
    }

    /**
     * Método responsável por ler um arquivo, armazenar seus dados no buffer e
     * chamar os métodos que filtram os dados, cria um novo arquivo de saída e
     * salvas os dados filtrados nele.
     *
     * @param nameFile Nome do arquivo a ser manipulado.
     */
    public void readingFiles(String nameFile) {

        boolean notEmpty;
        String lineFile;
        int numLine = 0;

        try {

            // Abre e carrega no buffer o arquivo cujo nome foi recebido por parâmetro(nameFile).
            BufferedReader readFile = new BufferedReader(new FileReader("files\\lists\\" + nameFile + ""));

            notEmpty = readFile.ready();

            // Verifica se existe conteúdo no arquivo informado.
            if (notEmpty) {

                while (readFile.ready()) {

                    lineFile = readFile.readLine();
                    numLine++;

                    //  System.out.println("Line [" + (numLine++) + "]: " + lineFile);

                    /**
                     * Chama o método que ira normalizar os dados da linha atual
                     * do arquivo, o retorno do método é armazenado na váriavel
                     * lineNormalized.
                     */
                 //   lineNormalized = (filter.stringNormalize(lineFile));
                    
                }
                // Closes the file.
                readFile.close();

            } else {

                System.out.println("The file selected is empty.");
            }

        } catch (IOException IOerror) {

            System.out.println("I/O ERROR: " + IOerror);

        } catch (Exception otherError) {

            System.out.println("Other Exception: " + otherError);
        }
    }
}
