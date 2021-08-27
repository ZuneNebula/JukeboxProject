package model;

public abstract class PlaylistEntry {
    private int sNo;
    private String name;
    private String artist;
    private java.sql.Time duration;
    private String type;

    PlaylistEntry(int sNo, String name, String artist, java.sql.Time duration, String type){
        this.sNo = sNo;
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.type = type;
    }

    public int getsNo(){
        return sNo;
    }
    public String getName(){
        return name;
    }
    public String getArtist(){
        return artist;
    }
    public java.sql.Time getDuration(){
        return duration;
    }
    public String getType(){
        return  type;
    }
}
