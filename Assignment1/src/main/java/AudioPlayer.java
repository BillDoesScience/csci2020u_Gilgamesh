/**
 * <h1>Audio Player Class</h1>
 * This is the primary Audio Player Class that plays the audio file that is passed
 * <p>
 *
 * @author  Kishore Muralitharan
 * @version 1.0
 * @since   2022-02-21
 * */
public class AudioPlayer implements MediaPlayer{
    /**
     * Adapter Object Variable assigned to the Class
     */
    Adapter Adpt;

    /** Decides how to best handle the audio file passed to it, and if is supported plays it other wise
     * passes to the adapter if file is of accepted type else fails
     * @param AudioType - The Type of Audio file that is being passed to the (eg. Mp4, VLC, Mp3)
     * @param audiofile - The File name for the audio file
     */
    @Override
    public void play(String AudioType, String audiofile) {
        if (AudioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 File. Filename: " + audiofile);
        } else if (AudioType.equalsIgnoreCase("vlc") || AudioType.equalsIgnoreCase("mp4")) {
            Adpt = new Adapter(AudioType);
            Adpt.play(AudioType,audiofile);
        } else{
            System.out.println("Invalid Media Type " + AudioType);
        }
    }
}
