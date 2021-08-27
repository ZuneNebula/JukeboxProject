package DAO;

import application.PodcastInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PodcastHandlerTest {

    PodcastInterface podcastHandler;
    @BeforeEach
    void setUp() {
        podcastHandler = new PodcastHandler();
    }

    @AfterEach
    void tearDown() {
        podcastHandler = null;
    }

    @Test
    void displayAll() {
        assertEquals(4,podcastHandler.displayAll().size());
    }

    @Test
    void getByCelebrity() {
        assertEquals(2,podcastHandler.getByCelebrity("Patrick Patrikios").size());
        assertEquals(1,podcastHandler.getByCelebrity("VYEN").size());
        assertEquals(0,podcastHandler.getByCelebrity("SOMETHING").size());
    }

    @Test
    void getByDate() {
        assertEquals(1,podcastHandler.getByDate(java.sql.Date.valueOf("2020-8-20")).size());
        assertEquals(1,podcastHandler.getByDate(java.sql.Date.valueOf("2021-5-3")).size());
        assertEquals(0,podcastHandler.getByDate(java.sql.Date.valueOf("2020-12-6")).size());
    }
}