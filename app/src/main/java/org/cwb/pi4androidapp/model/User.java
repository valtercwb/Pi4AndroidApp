package org.cwb.pi4androidapp.model;

import java.io.Serializable;

/**
 * Created by valter.franco on 11/2/2017.
 */

public class User implements Serializable {
    private String userId;
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * Returns a full name
     * @return - users full name
     */
    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", id='" + userId + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
