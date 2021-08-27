package model;

import java.util.Date;

public class Podcast  extends PlaylistEntry{

    private Date date;

    //constructors
    public Podcast(int sNo, String name, String celebrity, Date date, java.sql.Time duration){
        super(sNo,name,celebrity,duration,"Podcast");
        this.date = date;
    }

    //getters
    public Date getDate(){
        return date;
    }

    public String toString(){
        return String.format("%5d\t%30s\t%30s\t%20s\t%20s",getsNo(),getName(),getArtist(),getDate(),getDuration());
    }
}
