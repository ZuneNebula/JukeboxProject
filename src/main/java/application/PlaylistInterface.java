package application;

import model.Playlist;
import model.PlaylistEntry;
import model.User;

import java.util.List;

public interface PlaylistInterface {

    void addEntry(int entryNo, String name, User user, String type);//adds a playlist entry to the playlist

    List<PlaylistEntry> getByName(String name); //returns the playlist by playlist name

    List<Playlist> displayAll(User user); //returns all playlists made by the user
}
