package DAO;

import application.UserInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserHandlerTest {
    UserInterface userHandler;
    @BeforeEach
    void setUp() {
        userHandler = new UserHandler();
    }

    @AfterEach
    void tearDown() {
        userHandler = null;
    }

    @Test
    void retrieve() {
        assertEquals("mihir",userHandler.retrieve("mihir").getName());
        assertEquals(1,userHandler.retrieve("mihir").getUid());
        assertEquals("sharma",userHandler.retrieve("sharma").getName());
    }
}