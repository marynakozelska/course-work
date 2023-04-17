package org.example.dto;

import lombok.Data;
import org.example.entity.Customer;
import org.example.entity.Description;
import org.example.entity.Volunteer;
import org.example.enums.Category;

import java.util.ArrayList;

@Data
public class AdvertisementDTO {
    String id;
    String name;
    String category;
    Boolean isOpen;
    Customer customer;
    Description description;
    ArrayList<Volunteer> responded;

    public AdvertisementDTO(String id, String name, Category category, Boolean isOpen, Customer customer, Description description, ArrayList<Volunteer> responded) {
        this.id = id;
        this.name = name;
        this.category = categoryToString(category);
        this.isOpen = isOpen;
        this.customer = customer;
        this.description = description;
        this.responded = responded;
    }

    private String categoryToString(Category category) {
        return switch (category) {
            case CLOTHES -> "ОДЯГ";
            case FOOD -> "ЇЖА";
            case DRIVING -> "ВОДІННЯ";
            case EQUIPMENT -> "ТЕХНІКА";
        };
    }

    private Category stringToCategory(String dbValue) {
        return switch (dbValue) {
            case "ОДЯГ" -> Category.CLOTHES;
            case "ЇЖА" -> Category.FOOD;
            case "ВОДІННЯ" -> Category.DRIVING;
            case "ТЕХНІКА" -> Category.EQUIPMENT;
            default -> throw new IllegalArgumentException("Unknown status in the database: " + dbValue);
        };
    }
}
