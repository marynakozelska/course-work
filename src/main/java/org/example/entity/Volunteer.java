package org.example.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import org.example.enums.Skill;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;

@Container(containerName = "Volunteers")
public class Volunteer extends User {

    String location;
    @Enumerated(EnumType.STRING)
    ArrayList<Skill> skills;
    String about;

    public Volunteer(String name, String surname, String email, String phone, String location, ArrayList<Skill> skills, String about) {
        super(name, surname, email, phone);
        this.location = location;
        this.skills = skills;
        this.about = about;
    }

    public Volunteer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
