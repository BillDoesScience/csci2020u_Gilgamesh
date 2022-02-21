/**
 * <h1>MediaPlayer Interface</h1>
 * The Interface for the Media Player
 * <p>
 *
 * @author  Kishore Muralitharan
 * @version 1.0
 * @since   2022-02-21
 */
public interface MediaPlayer {
    /** Decides how to best handle the audio file passed to it, and if is supported plays it otherwise
     * passes to the adapter if file is of accepted type else fails, Overridden in AudioPlayer Class
     * @param AudioType - The Type of Audio file that is being passed to the (eg. Mp4, VLC, Mp3)
     * @param audiofile - The File name for the audio file
     */
    public void play(String AudioType, String audiofile);
}
