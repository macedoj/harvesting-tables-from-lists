/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file_handler;

/**
 * This class handle the data of the files
 * 
 * @since 05/11/2012 - Last change: 20/12/2012
 * @version 0.1
 * @author Juliano R. Macedo
 * @link https://github.com/JulianoR/Extract-Tables-from-Lists
 */
public class FileConfig {

    private String nameFile;
    private String rowFile;

    /**
     * Construction the row with data of the file
     *
     * @param rowData data of the row
     */
    public void constructionRow(String rowData) {
        setRowFile((getRowFile()) + rowData);
    }

    /**
     * @return the nameFile
     */
    public String getNameFile() {
        return nameFile;
    }

    /**
     * @param nameFile the nameFile to set
     */
    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    /**
     * @return the rowFile
     */
    public String getRowFile() {
        return rowFile;
    }

    /**
     * @param rowFile the rowFile to set
     */
    public void setRowFile(String rowFile) {
        this.rowFile = rowFile;
    }
}
