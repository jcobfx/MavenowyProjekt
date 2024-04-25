package org.example.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.*;

public class AccountManager {
    private final Connection databaseConnection;

    public AccountManager(Connection databaseConnection){
        this.databaseConnection = databaseConnection;
    }

    public void register(int id, String username, String password){
        try (PreparedStatement statement = databaseConnection.prepareStatement("INSERT INTO users VALUES(?,?,?)")){
            statement.setInt(1, id);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.execute();
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
        }
    }

    public boolean authenticate(String username, String password){
        try (PreparedStatement statement = databaseConnection.prepareStatement("SELECT name, password FROM users WHERE name = ?")){
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs != null){
                return rs.getString("password").equals(password);
            }
            else {
                System.out.println("Niepoprawne haslo dla uzytkownika: " + username);
                return false;
            }
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
        }
        return false;
    }

    public Account getAccount(String username){
        try (PreparedStatement statement = databaseConnection.prepareStatement("SELECT id, name FROM users WHERE name = ?")){
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            return new Account(rs.getInt(1), username);
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
        }
        return null;
    }

    public Account getAccount(int id){
        try (PreparedStatement statement = databaseConnection.prepareStatement("SELECT id, name FROM users WHERE id = ?")){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return new Account(id, rs.getString(2));
        }
        catch (SQLException e){
            e.printStackTrace(System.err);
        }
        return null;
    }
}
