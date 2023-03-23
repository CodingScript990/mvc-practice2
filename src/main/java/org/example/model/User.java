package org.example.model;

import java.util.Objects;

// User Class
public class User {
    // User Info -> userId, userName
    private String userId;
    private String userName;

    // User Constructor add
    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    // User Boolean type equalsUser method add
    public boolean equalsUser(User user) {
        return this.equals(user);
    }

    // User Boolean type Equals method -> Test [userId, userName] value 동일한지
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName);
    }

    // User Int type HashCode method
    @Override
    public int hashCode() {
        return Objects.hash(userId, userName);
    }
}
