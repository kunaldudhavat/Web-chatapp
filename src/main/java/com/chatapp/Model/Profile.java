package com.chatapp.Model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Profile {

    private String bio;
    private String location;
    private String website;

    public Profile() {
    }

    public Profile(String bio, String location, String website) {
        this.bio = bio;
        this.location = location;
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Profile [bio=" + bio + ", location=" + location + ", website=" + website + "]";
    }
}
