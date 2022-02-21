/**
 * <h1>AdvMediaPlayer Interface</h1>
 * The Interface for the Advanced Media Player
 * <p>
 *
 * @author  Kishore Muralitharan
 * @version 1.0
 * @since   2022-02-21
 */
public interface AdvMediaPlayer {
    /** Primary Player for this Class, handles and plays VLC files for the Adv. Media Player
     * to be Overridden in VLCPlayer Class
     * @param audiofile - Audio File to be played
     */
    public void playVLC(String audiofile);

    /** Primary Player for this Class, handles and plays Mp4 files for the Adv. Media Player
     * to be Overridden in MP4Player Class
     * @param audiofile - Audio File to be played
     */
    public void playMp4(String audiofile);
}
