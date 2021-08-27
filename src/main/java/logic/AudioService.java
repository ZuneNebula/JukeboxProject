package logic;

import model.PlaylistEntry;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class AudioService {

    static Scanner sc = new Scanner(System.in);

    public void playFromList(List<? extends PlaylistEntry> entries) { //add list controls

        int length = entries.size();
        for(int i = 0; i<length; i++){
            PlaylistEntry entry = entries.get(i);
            System.out.println("Currently playing");
            System.out.println(entries.get(i));
            if(i<length-1){
                System.out.println("Up next");
                System.out.println(entries.get(i+1));
            }
            play(entry.getName());
            System.out.println("List controls");
            System.out.println("P: Previous, N: Next, S: Shuffle, Q: Quit");
            String userChoice = sc.nextLine().toUpperCase();
            switch (userChoice){
                case "P":
                    i--;
                    break;
                case "N":
                    i++;
                    break;
                case "S":
                    Collections.shuffle(entries,new Random());
                    i =0;
                    break;
                case "Q":
                    return;
                default:
                    break;
            }

        }

    }

    public void play(String fileName){ //add single song controls
        fileName = fileName.toLowerCase();
        File file = new File("src/main/resources/"+fileName+".wav");
        try{
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            String userInput = "";
            while(!userInput.equalsIgnoreCase("Q")){
                System.out.println("Song Controls");
                System.out.println("P: Play, Q: Quit, R: Reset, S: Stop, L: Loop");
                userInput = sc.nextLine().toUpperCase();
                switch (userInput){
                    case "P":
                        clip.start();break;
                    case "Q":
                        clip.close();break;
                    case "R":
                        clip.setMicrosecondPosition(0);break;
                    case "S":
                        clip.stop();break;
                    case "L":
                        clip.loop(clip.LOOP_CONTINUOUSLY);break;
                    default:
                        break;
                }
            }
        }catch(LineUnavailableException | UnsupportedAudioFileException | IOException e){ //CATCHES AUDIO RELATED EXCEPTIONS
            System.out.println(e.getMessage());
        }
    }
}
