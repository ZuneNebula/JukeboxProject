package model;

public class User {

    private String name;
    private int uid;

    //constructor
    public User(int uid,String name){
        this.name = name;
        this.uid = uid;
    }

    public User() {

    }

    //getters
    public String getName(){
        return name;
    }
    public int getUid(){
        return uid;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setUid(int uid){
        this.uid = uid;
    }
}
