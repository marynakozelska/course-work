package org.example.dto;

import lombok.Data;
import org.example.enums.Skill;

import java.util.ArrayList;

@Data
public class VolunteerDTO {
    String id;
    String name;
    String surname;
    String email;
    String phone;
    String location;
    ArrayList<String> skills;
    String about;

    public VolunteerDTO(String id, String name, String surname, String email, String phone, String location, ArrayList<Skill> skills, String about) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.skills = new ArrayList<>(
                skills
                        .stream()
                        .map(this::skillToString)
                        .toList()
        );
        this.about = about;
    }

    private String skillToString(Skill skill) {
        return switch (skill) {
            case COOKING -> "ПРИГОТУВАННЯ ЇЖІ";
            case DRIVING -> "ВОДІННЯ";
            case FUNDING -> "ФІНАНСУВАННЯ";
            case SHOPPING -> "ПОКУПКИ";
        };
    }
}
