package application;
import model.Song;
import java.util.List;

public interface SongInterface {

    List<Song> displayAll(); //displays all songs
    List<Song> getByArtist(String artist); //displays all songs by an artist
    List<Song> getByAlbum(String album); //displays all songs in an album
    List<Song> getByGenre(String genre); //displays all songs in a genre
}
