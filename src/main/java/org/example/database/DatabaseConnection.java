package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection databaseConnection;

    public Connection getDatabaseConnection() {
        return databaseConnection;
    }

    public void connect(String databasePath) {
        try {
            databaseConnection = DriverManager.getConnection(databasePath);
            if (databaseConnection != null)
                System.out.println("Poprawnie utworzono połączenie z bazą: " + databaseConnection.getMetaData());
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
        }
    }

    public void disconnect(){
        try {
            databaseConnection.close();
            if (databaseConnection.isClosed())
                System.out.println("Poprawnie zamknięto połączenie z bazą.");
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
        }
    }
}
