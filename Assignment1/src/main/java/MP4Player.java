/**
 * <h1>MP4 Player Class</h1>
 * Sub Function of the Adv. Media Player handles Mp4 files
 * <p>
 *
 * @author  Kishore Muralitharan
 * @version 1.0
 * @since   2022-02-21
 */
public class MP4Player implements  AdvMediaPlayer {

    /** This Class exists to eliminate errors, functionally does nothing
     * @param audiofile - The Audio file to be played
     */
    @Override
    public void playVLC(String audiofile) {
        // Nothing
    }

    /** Primary Player for this Class, handles and plays Mp4 files for the Adv. Media Player
     * @param audiofile - Audio File to be played
     */
    @Override
    public void playMp4(String audiofile) {
        System.out.println("Playing MP4 File, Named: " + audiofile);
    }
}
