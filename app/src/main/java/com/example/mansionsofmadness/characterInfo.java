package com.example.mansionsofmadness;

import android.content.Intent;
import android.graphics.drawable.Drawable;

// Class to store each characters information
public class characterInfo {
    // Variable declarations
    String name;
    String image;
    String bio;
    String ability;

    int stat_Health;
    int stat_Sanity;
    int stat_Strength;
    int stat_Agility;
    int stat_Lore;
    int stat_Observation;
    int stat_Influence;
    int stat_Will;


    // Use Alt+insert to automatically generate this stuff
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getStat_Health() {
        return stat_Health;
    }

    public void setStat_Health(int stat_Health) {
        this.stat_Health = stat_Health;
    }

    public int getStat_Sanity() {
        return stat_Sanity;
    }

    public void setStat_Sanity(int stat_Sanity) {
        this.stat_Sanity = stat_Sanity;
    }

    public int getStat_Strength() {
        return stat_Strength;
    }

    public void setStat_Strength(int stat_Strength) {
        this.stat_Strength = stat_Strength;
    }

    public int getStat_Agility() {
        return stat_Agility;
    }

    public void setStat_Agility(int stat_Agility) {
        this.stat_Agility = stat_Agility;
    }

    public int getStat_Lore() {
        return stat_Lore;
    }

    public void setStat_Lore(int stat_Lore) {
        this.stat_Lore = stat_Lore;
    }

    public int getStat_Observation() {
        return stat_Observation;
    }

    public void setStat_Observation(int stat_Observation) {
        this.stat_Observation = stat_Observation;
    }

    public int getStat_Influence() {
        return stat_Influence;
    }

    public void setStat_Influence(int stat_Influence) {
        this.stat_Influence = stat_Influence;
    }

    public int getStat_Will() {
        return stat_Will;
    }

    public void setStat_Will(int stat_Will) {
        this.stat_Will = stat_Will;
    }

    @Override
    public String toString() {
        return "characterInfo{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", bio='" + bio + '\'' +
                ", ability='" + ability + '\'' +
                ", stat_Health=" + stat_Health +
                ", stat_Sanity=" + stat_Sanity +
                ", stat_Strength=" + stat_Strength +
                ", stat_Agility=" + stat_Agility +
                ", stat_Lore=" + stat_Lore +
                ", stat_Observation=" + stat_Observation +
                ", stat_Influence=" + stat_Influence +
                ", stat_Will=" + stat_Will +
                '}';
    }
}


