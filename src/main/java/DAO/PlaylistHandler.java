package DAO;

import logic.DBSetup;
import application.PlaylistInterface;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistHandler implements PlaylistInterface {

    public int getEntry(int sno, String type){
        String query1 = "select entryno from songs where sno="+sno;
        String query2 = "select entryno from podcasts where sno="+sno;
        int entryno =0;
        try(Connection con = new DBSetup().getConnection()){
            Statement st = con.createStatement();
            ResultSet rs;
            if(type.toLowerCase().equals("song")){
                rs = st.executeQuery(query1);
            }
            else{
                rs = st.executeQuery(query2);
            }
            while(rs.next()){
                entryno = rs.getInt(1);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return entryno;
    }

    public void addEntry(int sno, String name, User user, String type){
        int entryNo = getEntry(sno,type);
        int uid = user.getUid();

        String query1 = "select entryno from playlist where name =? and userno = ? and entryno = ?";
        String query2 = "insert into playlist values(default,?,?,?);";

        try(Connection con = new DBSetup().getConnection()){
            PreparedStatement st = con.prepareStatement(query1);
            st.setString(1,name);
            st.setInt(2,uid);
            st.setInt(3,entryNo);

            ResultSet rs = st.executeQuery();

            if(!rs.next()){
                PreparedStatement st2 = con.prepareStatement(query2);
                st2.setInt(1,entryNo);
                st2.setString(2,name);
                st2.setInt(3,uid);

                int rows2 = st2.executeUpdate();

            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<PlaylistEntry> getByName(String playlistName){ //returns all entries in a playlist
        String query1 = "select playlistentry.sno, playlistentry.name,playlistentry.artist,playlistentry.duration,playlistentry.type\n" +
                "from playlistentry join playlist on playlistentry.sno = playlist.entryno\n" +
                "where playlist.name = '"+playlistName+"'";

        List<PlaylistEntry> entries = new ArrayList<PlaylistEntry>();

        try(Connection con = new DBSetup().getConnection()){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);

            while(rs.next()){
                int sno = rs.getInt(1);
                String name = rs.getString(2);
                String artist = rs.getString(3);
                java.sql.Time duration = rs.getTime(4);
                String type = rs.getString(5);

                if(type.toLowerCase().equals("song")){

                    String query2 = "select songs.album, songs.genre from songs\n" +
                            "join playlistentry on songs.entryno = playlistentry.sno\n" +
                            "where playlistentry.sno ="+sno;

                    Statement st2 = con.createStatement();
                    ResultSet rs2 = st2.executeQuery(query2);

                    while(rs2.next()){
                        String album = rs2.getString(1);
                        String genre = rs2.getString(2);
                        entries.add(new Song(sno,name,artist,genre,album,duration));
                    }
                }
                else{
                    String query3 = "select podcasts.date from podcasts\n" +
                            "join playlistentry on podcasts.entryno = playlistentry.sno\n" +
                            "where playlistentry.sno = "+sno;

                    Statement st3 = con.createStatement();
                    ResultSet rs3 = st3.executeQuery(query3);

                    while(rs3.next()){
                        java.sql.Date date = rs3.getDate(1);
                        entries.add(new Podcast(sno,name,artist,date,duration));
                    }
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return entries;
    }

    public List<Playlist> displayAll(User user){ //returns all playlists made by the user
        String query = "select name from playlist where userno = "+user.getUid()+" group by name";

        List<Playlist> playlists = new ArrayList<Playlist>();
        try(Connection con = new DBSetup().getConnection()){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                String pName = rs.getString(1);
                playlists.add(new Playlist(pName,user,getByName(pName)));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return playlists;
    }
}
