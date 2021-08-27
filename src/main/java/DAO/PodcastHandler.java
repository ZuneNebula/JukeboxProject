package DAO;

import logic.DBSetup;
import application.PodcastInterface;
import model.Podcast;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PodcastHandler implements PodcastInterface {

    private List<Podcast> handleQuery(String query){
        List<Podcast> podcastList = new ArrayList<Podcast>();
        try(Connection con = new DBSetup().getConnection()){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                podcastList.add(new Podcast(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getTime(5)));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return podcastList;
    }

    public List<Podcast> displayAll(){ //displays all podcasts

        String query = "select podcasts.sno, playlistentry.name, playlistentry.artist, podcasts.date, playlistentry.duration\n" +
                "from playlistentry join podcasts on playlistentry.sno = podcasts.entryno";

        return handleQuery(query);
    }
    public List<Podcast> getByCelebrity(String celebrity){ //displays all songs by celebrities

        String query = "select podcasts.sno, playlistentry.name, playlistentry.artist, podcasts.date, playlistentry.duration\n" +
                "from playlistentry join podcasts on playlistentry.sno = podcasts.entryno\n" +
                "where playlistentry.artist = '"+celebrity+"';";

        return handleQuery(query);

    }
    public List<Podcast> getByDate(Date date){ //displays all podcasts added on a date

        String query = "select podcasts.sno, playlistentry.name, playlistentry.artist, podcasts.date, playlistentry.duration\n" +
                "from playlistentry join podcasts on playlistentry.sno = podcasts.entryno\n" +
                "where podcasts.date = '"+date+"';";

        return handleQuery(query);
    }
}
