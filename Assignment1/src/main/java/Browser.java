/**
 * <h1>Browser Class</h1>
 *  Acts in place of the fake browser which then calls the file handlers
 * <p>
 *
 * @author  Kishore Muralitharan
 * @version 1.0
 * @since   2022-02-21
 * */
public class Browser implements FileHandler{
    /**
     * Adapter Object Variable assigned to the Class
     */
    Adapter Adpt;

    /** Decides how to best handle the file passed to it, and if is supported, otherwise
     * passes to the adapter if file is of accepted type else fails
     * @param fileType - The Type of file that is being passed (eg. txt, pdf, docx)
     * @param file - The File name
     */
    @Override
    public void open(String fileType, String file) {
        if (fileType.equalsIgnoreCase("txt")) {
            System.out.println("Opening Text File. Filename: " + file);
        } else if (fileType.equalsIgnoreCase("pdf") || fileType.equalsIgnoreCase("docx")) {
            Adpt = new Adapter(fileType);
            Adpt.open(fileType,file);
        } else{
            System.out.println("Invalid Media Type " + fileType);
        }
    }
}
