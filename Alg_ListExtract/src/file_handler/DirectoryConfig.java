/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file_handler;

/**
 *
 * @author PC
 */
public class DirectoryConfig {

    private String extractDirectory;
    private String outDirectory;
    private static DirectoryConfig dataDirectory;

    private DirectoryConfig() {
    }

    /**
     * 
     * @return the instance of this class
     */
    public static DirectoryConfig getInstance() {

        if (dataDirectory == null) {
            dataDirectory = new DirectoryConfig();
        }
        return dataDirectory;
    }

    /**
     * @return the extractDirectory
     */
    public String getExtractDirectory() {
        return extractDirectory;
    }

    /**
     *
     * @param extractDirectory the extractDirectory to set
     */
    public void setExtractDirectory(String extractDirectory) {
        this.extractDirectory = extractDirectory;
    }

    /**
     *
     * @return the outDirectory
     */
    public String getOutDirectory() {
        return outDirectory;
    }

    /**
     *
     * @param outDirectory the outDirectory to set
     */
    public void setOutDirectory(String outDirectory) {
        this.outDirectory = outDirectory;
    }
}
