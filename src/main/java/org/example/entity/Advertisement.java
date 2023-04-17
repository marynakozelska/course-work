package org.example.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.enums.Category;
import org.springframework.data.annotation.Id;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Container(containerName = "Advertisements")
public class Advertisement {
    @Id
    @GeneratedValue
    String id;
    @NotNull
    String name;
    @Enumerated(EnumType.STRING)
    Category category;
    @JsonProperty("isOpen")
    Boolean isOpen;
    @NotNull
    @Valid
    Customer customer;
    @NotNull
    Description description;
    ArrayList<Volunteer> responded;

    public Advertisement(String name, Category category, Boolean isOpen, Customer customer, Description description, ArrayList<Volunteer> responded) {
        this.name = name;
        this.category = category;
        this.isOpen = isOpen;
        this.customer = customer;
        this.description = description;
        this.responded = responded;
    }

    public Advertisement(String id, String name, Category category, Boolean isOpen, Customer customer, Description description, ArrayList<Volunteer> responded) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.isOpen = isOpen;
        this.customer = customer;
        this.description = description;
        this.responded = responded;
    }

    public Advertisement() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getIsOpen() {
        if (this.description.getUntilDate().before(Timestamp.valueOf(LocalDateTime.now()))) {
            this.isOpen = false;
        }
        if (isOpen == null) {
            isOpen = true;
        }
        return isOpen;
    }

    public void setIsOpen(Boolean open) {
        isOpen = open;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public ArrayList<Volunteer> getResponded() {
        return responded;
    }

    public void setResponded(ArrayList<Volunteer> responded) {
        this.responded = responded;
    }
}
