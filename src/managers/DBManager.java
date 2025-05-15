package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    public static Connection connect()
    { Connection con = null;
        try {
         //   Class.forName("org.sqlite.jdbc");
           con= DriverManager.getConnection("jdbc:sqlite:tanks.db");
            System.out.println("Connected!");
        } catch (SQLException e) {


            throw new RuntimeException(e);


        }
        return con;
    }
}
