package DAO;

import application.SongInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongHandlerTest {
    SongInterface songHandler;
    @BeforeEach
    void setUp() {
        songHandler = new SongHandler();
    }

    @AfterEach
    void tearDown() {
        songHandler = null;
    }

    @Test
    void displayAll() {
        assertEquals(7,songHandler.displayAll().size());
    }

    @Test
    void getByArtist() {
        assertEquals(2,songHandler.getByArtist("Squadda B").size());
        assertEquals(1,songHandler.getByArtist("RKVC").size());
        assertEquals(0,songHandler.getByArtist("random").size());
    }

    @Test
    void getByAlbum() {
        assertEquals(2,songHandler.getByAlbum("Bb").size());
        assertEquals(1,songHandler.getByAlbum("EA").size());
        assertEquals(0,songHandler.getByAlbum("what").size());
    }

    @Test
    void getByGenre() {
        assertEquals(3,songHandler.getByGenre("Rock").size());
        assertEquals(2,songHandler.getByGenre("Hip Hop").size());
        assertEquals(0,songHandler.getByGenre("roman").size());
    }
}