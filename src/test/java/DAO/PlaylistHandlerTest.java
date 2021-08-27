package DAO;

import application.PlaylistInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistHandlerTest {
    PlaylistInterface playlistHandler;
    @BeforeEach
    void setUp() {
        playlistHandler = new PlaylistHandler();
    }

    @AfterEach
    void tearDown() {
        playlistHandler = null;
    }

    @Test
    void getByName() {
        assertEquals(0,playlistHandler.getByName("battle music").size());
        assertEquals(1,playlistHandler.getByName("dusk").size());
    }

}