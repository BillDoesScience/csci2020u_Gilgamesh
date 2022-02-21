/**
 * <h1>VLC Player Class</h1>
 * Sub Function of the Adv. Media Player handles VLC files
 * <p>
 *
 * @author  Kishore Muralitharan
 * @version 1.0
 * @since   2022-02-21
 */
public class VLCPlayer implements AdvMediaPlayer {


    /** Primary Player for this Class, handles and plays VLC files for the Adv. Media Player
     * @param audiofile - Audio File to be played
     */
    @Override
    public void playVLC(String audiofile) {
        System.out.println("Playing VLC File. Named: " + audiofile);
    }

    /** This Class exists to eliminate errors, functionally does nothing
     * @param audiofile - The Audio file to be played
     */
    @Override
    public void playMp4(String audiofile) {
         // Do Nothing
    }
}
