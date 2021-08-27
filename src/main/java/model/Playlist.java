package model;
import java.util.List;

public class Playlist  {

    private String playlistName;
    private User creator;
    private List<PlaylistEntry> entries;

    //constructor
    public Playlist(String playlistName, User creator, List<PlaylistEntry> entries){
        this.playlistName = playlistName;
        this.creator = creator;
        this.entries = entries;
    }

    //getters
    public String getPlaylistName(){
        return playlistName;
    }
    public User getCreator(){
        return  creator;
    }
    public List<PlaylistEntry> getEntries(){
        return  entries;
    }

    public String toString(){
        return String.format("%30s",getPlaylistName());
    }

    public String displayPlaylist(){
        return entries.toString();
    }

}
