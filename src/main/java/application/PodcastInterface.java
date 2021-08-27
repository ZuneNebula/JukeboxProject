package application;
import model.Podcast;

import java.util.Date;
import java.util.List;

public interface PodcastInterface {

    List<Podcast> displayAll(); //displays all podcasts
    List<Podcast> getByCelebrity(String celebrity); //displays all songs by celebrities
    List<Podcast> getByDate(Date date); //displays all podcasts added on a date
}
