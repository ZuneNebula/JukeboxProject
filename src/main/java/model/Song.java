package model;

public class Song extends PlaylistEntry{

    private String album;
    private String genre;

    //constructors
    public Song(int sNo, String name, String artist, String genre, String album, java.sql.Time duration){
        super(sNo, name,artist,duration,"Song");
        this.genre = genre;
        this.album = album;
    }

    //getters
    public String getAlbum(){
        return album;
    }
    public String getGenre(){
        return genre;
    }

    public String toString(){
        return String.format("%5d\t%30s\t%30s\t%15s\t%15s\t%20s",getsNo(),getName(),getArtist(),getGenre(),getAlbum(),getDuration());
    }

}
