/**
 * <h1>Doc File Opener Class</h1>
 * Sub Function of the Adv. File Opener that handles Docx. files
 * <p>
 *
 * @author  Kishore Muralitharan
 * @version 1.0
 * @since   2022-02-21
 */
public class DocFileOpener implements AdvFileOpener {


    /** Primary File Handler for this class, then opens the docx file
     * @param file - File to be played
     */
    @Override
    public void openDoc(String file) {
        System.out.println("Opening PDF File. Named: " + file);
    }

    /**
     * This Function does nothing, Just Error suppression
     * @param file - File to be played
     */
    @Override
    public void openPDF(String file) {};
}
