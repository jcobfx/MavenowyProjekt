package org.example.auth;

public record Account(int id, String username) {
    @Override
    public String toString() {
        return "[" + id + "] " + username;
    }
}
