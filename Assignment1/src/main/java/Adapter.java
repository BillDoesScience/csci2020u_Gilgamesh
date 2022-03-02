/**
 * <h1>Adapter Class</h1>
 * Adapter that handles any calls from the Browser to the Adv. File Handler
 * <p>
 *
 * @author  Kishore Muralitharan
 * @version 1.0
 * @since   2022-02-21
 */
public class Adapter implements FileHandler {
    /**
     * Advaned File Opener attached to the Class Instance
     */
    AdvFileOpener AdvFile;

    /** Constructor for the Adapter
     * @param filetype Determines the type of file opener to be created with this instance of Adv. File Opener
     */
    public Adapter(String filetype) {
        if (filetype.equalsIgnoreCase("pdf")) {
            AdvFile = new PDFFileOpener();
        } else if (filetype.equalsIgnoreCase("docx")) {
            AdvFile = new DocFileOpener();
        }
    }

    /** Primary file handler that determines the type of sub-system that the Adv. File Handler must use
     * @param filetype - The Type of the file passed
     * @param file - The File that is to be opened
     */
    @Override
    public void open(String filetype, String file) {
        if (filetype.equalsIgnoreCase("pdf")) {
            AdvFile.openPDF(file);
        } else if (filetype.equalsIgnoreCase("docx")) {
            AdvFile.openDoc(file);
        }
    }
}
