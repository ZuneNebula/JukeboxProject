package DAO;

import logic.DBSetup;
import application.UserInterface;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserHandler implements UserInterface {

    public User retrieve(String name){
        User user = new User();
        try(Connection con = new DBSetup().getConnection()){
            String query1 = "select * from users where name ='"+name+"'";
            String query2 = "insert into users values(default,'"+name+"')";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);

            if (!rs.next()) {
                Statement st2 = con.createStatement();
                st2.executeUpdate(query2);
            }
            rs = st.executeQuery(query1);
            while(rs.next()){
                return new User(rs.getInt(1), rs.getString(2));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return user;
    }
}
