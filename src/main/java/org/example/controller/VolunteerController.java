package org.example.controller;

import org.example.dto.VolunteerDTO;
import org.example.entity.Volunteer;
import org.example.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.*;

@Controller
@RequestMapping("/volunteers")
public class VolunteerController {
    private VolunteerRepository volunteerRepository;
    private Validator validator;

    @Autowired
    public VolunteerController(VolunteerRepository volunteerRepository, Validator validator) {
        this.volunteerRepository = volunteerRepository;
        this.validator = validator;
    }

    @GetMapping
    public ResponseEntity<List<VolunteerDTO>> getAllVolunteer() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteerRepository.findAll().iterator().forEachRemaining(volunteers::add);

        List<VolunteerDTO> volunteerDTOS = volunteers
                .stream()
                .map(this::toDTO)
                .toList();

        return new ResponseEntity<>(volunteerDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerDTO> getVolunteer(@PathVariable("id") String id) {
        Optional<Volunteer> volunteer = volunteerRepository.findById(id);
        if (volunteer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        VolunteerDTO volunteerDTO = toDTO(volunteer.get());
        return new ResponseEntity<>(volunteerDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Volunteer> addVolunteer(@Valid @RequestBody Volunteer volunteer) {
        Set<ConstraintViolation<Volunteer>> violations = validator.validate(volunteer);
        if (!violations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Volunteer newVolunteer = volunteerRepository.save(volunteer);
        return new ResponseEntity<>(newVolunteer, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Volunteer> deleteVolunteer(@PathVariable("id") String id) {
        if (volunteerRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        volunteerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private VolunteerDTO toDTO(Volunteer volunteer) {
        return new VolunteerDTO(volunteer.getId(),
                volunteer.getName(),
                volunteer.getSurname(),
                volunteer.getEmail(),
                volunteer.getPhone(),
                volunteer.getLocation(),
                volunteer.getSkills(),
                volunteer.getAbout()
        );
    }
}
