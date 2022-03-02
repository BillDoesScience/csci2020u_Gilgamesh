/**
 * <h1>AdvFileOpener Interface</h1>
 * The Interface for the Advanced File Opener
 * <p>
 *
 * @author  Kishore Muralitharan
 * @version 1.0
 * @since   2022-02-21
 */
public interface AdvFileOpener {

    /** Primary File Handler for this class, then opens the PDF file
     * @param file - File to be played
     */
    public void openPDF(String file);


    /** Primary File Handler for this class, then opens the docx file
     * @param file - File to be played
     */
    public void openDoc(String file);
}
