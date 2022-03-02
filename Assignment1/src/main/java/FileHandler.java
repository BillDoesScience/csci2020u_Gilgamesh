/**
 * <h1>FileHandler Interface</h1>
 * The Interface for the Media Player
 * <p>
 *
 * @author  Kishore Muralitharan
 * @version 1.0
 * @since   2022-02-21
 */
public interface FileHandler {
    /** Decides how to best handle the file passed to it, and if is supported plays it otherwise
     * passes to the adapter if file is of accepted type else fails, Overridden in Browser Class
     * @param FileType - The Type of file that is being passed to the (eg. pdf, docx, txt)
     * @param File - The File name
     */
    public void open(String FileType, String File);
}
