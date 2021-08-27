package application;

import model.User;

public interface UserInterface {

    User retrieve(String name); //gets the user if if exists, else adds them
}
