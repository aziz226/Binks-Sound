package com.exopen.binkssound.Class;

public class User {
    String name, artisteName, city, profilImage;

    public User(String name, String artisteName, String city, String profilImage) {
        this.name = name;
        this.artisteName = artisteName;
        this.city = city;
        this.profilImage = profilImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtisteName() {
        return artisteName;
    }

    public void setArtisteName(String artisteName) {
        this.artisteName = artisteName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfilImage() {
        return profilImage;
    }

    public void setProfilImage(String profilImage) {
        this.profilImage = profilImage;
    }
}
