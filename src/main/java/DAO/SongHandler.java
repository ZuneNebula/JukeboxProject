package DAO;

import logic.DBSetup;
import application.SongInterface;
import model.Song;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class SongHandler implements SongInterface {

    private List<Song> handleQuery(String query){
        List<Song> songList = new ArrayList<Song>();

        try(Connection con = new DBSetup().getConnection()){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                songList.add(new Song(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getTime(6)));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return songList;
    }

    public List<Song> displayAll(){ //displays all songs

        String query = "select songs.sno, playlistentry.name, playlistentry.artist, songs.album, songs.genre, playlistentry.duration\n" +
                "from playlistentry join songs on playlistentry.sno = songs.entryno;\n";

        return handleQuery(query);
    }

    public List<Song> getByArtist(String artist){//displays all songs by an artist

        String query = "select songs.sno, playlistentry.name, playlistentry.artist, songs.album, songs.genre, playlistentry.duration\n" +
                "from playlistentry join songs on playlistentry.sno = songs.entryno\n" +
                "where playlistentry.artist ='"+artist+"'";

        return handleQuery(query);

    }
    public List<Song> getByAlbum(String album){ //displays all songs in an album

        String query = "select songs.sno, playlistentry.name, playlistentry.artist, songs.album, songs.genre, playlistentry.duration\n" +
                "from playlistentry join songs on playlistentry.sno = songs.entryno\n" +
                "where songs.album ='"+album+"'";

        return handleQuery(query);
    }
    public List<Song> getByGenre(String genre){ //displays all songs in a genre

        String query = "select songs.sno, playlistentry.name, playlistentry.artist, songs.album, songs.genre, playlistentry.duration\n" +
                "from playlistentry join songs on playlistentry.sno = songs.entryno\n" +
                "where songs.genre ='"+genre+"'";

        return handleQuery(query);

    }
}
