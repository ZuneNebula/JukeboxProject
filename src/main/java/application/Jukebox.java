package application;
import DAO.PlaylistHandler;
import DAO.PodcastHandler;
import DAO.SongHandler;
import DAO.UserHandler;
import logic.AudioService;
import model.*;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Jukebox {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        try{
            new Jukebox().musicService();
        }
        catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
        catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }

    }

    public void musicService() throws NumberFormatException,InputMismatchException{
        int userChoice = 0;
        do{
           System.out.println("Main Menu");
           System.out.println("1: Listen to songs");
           System.out.println("2: Listen to podcasts");
           System.out.println("3: Listen to playlists");
           System.out.println("4: Quit");
           userChoice = Integer.parseInt(sc.nextLine());

           switch(userChoice){
               case 1:songService();break;
               case 2: podcastService();break;
               case 3: playlistService();break;
               default:break;
           }
        }while(userChoice!=4);
    }

    public void songService() throws NumberFormatException, InputMismatchException{
        int userChoice = 0;
        SongInterface songHandler = new SongHandler();
        do{
            System.out.println("Song Menu");
            System.out.println("1: Display all songs");
            System.out.println("2: Get songs by artist");
            System.out.println("3: Get songs by album");
            System.out.println("4: Get songs by genre");
            System.out.println("5: Quit");
            userChoice = Integer.parseInt(sc.nextLine());

            switch(userChoice){
                case 1:
                    List<Song> songList = songHandler.displayAll();
                    songList.stream().filter(p->p!=null).forEach(i->System.out.println(i));
                    audioConnector(songList);
                    break;
                case 2:
                    System.out.println("Enter name of the artist");
                    List<Song> songListByArtist = songHandler.getByArtist(sc.nextLine());
                    songListByArtist.stream().filter(p->p!=null).forEach(i->System.out.println(i));
                    audioConnector(songListByArtist);
                    break;
                case 3:
                    System.out.println("Enter name of the album");
                    List<Song> songListByAlbum = songHandler.getByAlbum(sc.nextLine());
                    songListByAlbum.stream().filter(p->p!=null).forEach(i->System.out.println(i));
                    audioConnector(songListByAlbum);
                    break;
                case 4:
                    System.out.println("Enter name of the genre");
                    List<Song> songListByGenre = songHandler.getByGenre(sc.nextLine());
                    songListByGenre.stream().filter(p->p!=null).forEach(i->System.out.println(i));
                    audioConnector(songListByGenre);
                    break;
                default:
                    break;
            }
        }while(userChoice!=5);
    }

    public void podcastService() throws NumberFormatException,InputMismatchException{
        int userChoice = 0;
        PodcastInterface podcastHandler = new PodcastHandler();
        do{
            System.out.println("Song Menu");
            System.out.println("1: Display all podcasts");
            System.out.println("2: Get podcasts by celebrity");
            System.out.println("3: Get podcasts by date");
            System.out.println("4: Quit");
            userChoice = Integer.parseInt(sc.nextLine());

            switch(userChoice){
                case 1:
                    List<Podcast> podcastList = podcastHandler.displayAll();
                    podcastList.stream().filter(p->p!=null).forEach(i->System.out.println(i));
                    audioConnector(podcastList);
                    break;
                case 2:
                    System.out.println("Enter name of the celebrity");
                    List<Podcast> podcastListByCelebrity = podcastHandler.getByCelebrity(sc.nextLine());
                    podcastListByCelebrity.stream().filter(p->p!=null).forEach(i->System.out.println(i));
                    audioConnector(podcastListByCelebrity);
                    break;
                case 3:
                    System.out.println("Enter date in (yyyy-mm-dd)");
                    Date date = java.sql.Date.valueOf(sc.nextLine());
                    List<Podcast> podcastListByDate = podcastHandler.getByDate(date);
                    podcastListByDate.stream().filter(p->p!=null).forEach(i->System.out.println(i));
                    audioConnector(podcastListByDate);
                    break;
                default:
                    break;
            }
        }while(userChoice!=4);
    }

    public void playlistService() throws NumberFormatException, InputMismatchException {
        int userChoice = 0;
        UserInterface userHandler = new UserHandler();
        PlaylistInterface playlistHandler = new PlaylistHandler();
        SongInterface songHandler = new SongHandler();
        PodcastInterface podcastHandler = new PodcastHandler();

        System.out.println("Enter Username");
        String username = sc.nextLine();
        User user = userHandler.retrieve(username);
        do{
            System.out.println("Playlist Menu");
            System.out.println("1: Display all playlists");
            System.out.println("2: Get playlists by name");
            System.out.println("3: Create or Add to playlist");
            System.out.println("4: Quit");
            userChoice = Integer.parseInt(sc.nextLine());
            switch(userChoice){
                case 1:
                    List<Playlist> playlistList = playlistHandler.displayAll(user);
                    playlistList.stream().filter(p->p!=null).forEach(i->System.out.println(i));
                    break;
                case 2:
                    System.out.println("Enter name of the playlist");
                    List<PlaylistEntry> entries = playlistHandler.getByName(sc.nextLine());
                    entries.stream().filter(p->p!=null).forEach(i->System.out.println(i));
                    audioConnector(entries);
                    break;
                case 3:
                    System.out.println("Enter playlist name");
                    String playlistName = sc.nextLine();
                    int entryChoice =0;
                    do{
                        System.out.println("1: Add song");
                        System.out.println("2: Add podcast");
                        System.out.println("3: Quit");
                        entryChoice = Integer.parseInt(sc.nextLine());

                        switch(entryChoice){
                            case 1:
                                List<Song> songList = songHandler.displayAll();
                                songList.stream().filter(p->p!=null).forEach(i->System.out.println(i));
                                System.out.println("Enter song number to add to your playlist");
                                int songNo = Integer.parseInt(sc.nextLine());
                                playlistHandler.addEntry(songNo,playlistName,user,"song");
                                break;
                            case 2:
                                List<Podcast> podcastList = podcastHandler.displayAll();
                                podcastList.stream().filter(p->p!=null).forEach(i->System.out.println(i));
                                System.out.println("Enter song number to add to your playlist");
                                int podcastNo = Integer.parseInt(sc.nextLine());
                                playlistHandler.addEntry(podcastNo,playlistName,user,"podcast");
                                break;
                            default:
                                break;
                        }
                    }while(entryChoice!=3);
                default:
                    break;
            }
        }while(userChoice!=4);
    }

    public void audioConnector(List<? extends PlaylistEntry> list) {
        AudioService audioService = new AudioService();
        System.out.println("Do you want to search for a specific song? (yes/no)");
        String searchChoice = sc.nextLine();
        if(searchChoice.equalsIgnoreCase("yes")){
            System.out.println("Enter name of the song");
            audioService.play(sc.nextLine());
        }
        System.out.println("Do you want to listen to the current list of songs?(yes/no)");
        String userChoice = sc.nextLine();
        if(userChoice.equalsIgnoreCase("yes")){
            audioService.playFromList(list);
        }
    }

}
