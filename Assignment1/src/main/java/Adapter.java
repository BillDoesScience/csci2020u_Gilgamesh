/**
 * <h1>Adapter Class</h1>
 * Adapter that handles any calls from the Basic Audio Player to use The
 * Adv. Media Player Functions
 * <p>
 *
 * @author  Kishore Muralitharan
 * @version 1.0
 * @since   2022-02-21
 */
public class Adapter implements MediaPlayer {
    /**
     * Advaned Media Player Object attached to the Class Instance
     */
    AdvMediaPlayer AdvPlyr;

    /** Constructor for the Adapter
     * @param audiotype Determines the type of player to be created with this instance of Adv. Media Player
     */
    public Adapter(String audiotype) {
        if (audiotype.equalsIgnoreCase("vlc")) {
            AdvPlyr = new VLCPlayer();
        } else if (audiotype.equalsIgnoreCase("mp4")) {
            AdvPlyr = new MP4Player();
        }
    }

    /** Primary player class that uses the Adv. Player to play the determiend Audio Type
     * @param AudioType - The Audio Type of the file passed
     * @param audiofile - The Audio File that is to be played
     */
    @Override
    public void play(String AudioType, String audiofile) {
        if (AudioType.equalsIgnoreCase("vlc")) {
            AdvPlyr.playVLC(audiofile);
        } else if (AudioType.equalsIgnoreCase("mp4")) {
            AdvPlyr.playMp4(audiofile);
        }
    }
}
