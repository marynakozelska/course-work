package org.example.controller;

import org.example.dto.AdvertisementDTO;
import org.example.entity.Advertisement;
import org.example.entity.Customer;
import org.example.entity.Volunteer;
import org.example.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdvertisementController {
    AdvertisementRepository advertisementRepository;
    private Validator validator;

    @Autowired
    public AdvertisementController(AdvertisementRepository advertisementRepository, Validator validator) {
        this.advertisementRepository = advertisementRepository;
        this.validator = validator;
    }

    @GetMapping
    public ResponseEntity<List<AdvertisementDTO>> getAllAdvertisements() {
        List<Advertisement> advertisements = new ArrayList<>();
        advertisementRepository.findAll().forEach(advertisements::add);

        List<AdvertisementDTO> advertisementDTOS = advertisements
                .stream()
                .map(this::toDTO)
                .toList();

        return new ResponseEntity<>(advertisementDTOS, HttpStatus.OK);
    }

    @GetMapping("request/{id}")
    public ResponseEntity<AdvertisementDTO> getAdvertisement(@PathVariable("id") String id) {
        Optional<Advertisement> ad = advertisementRepository.findById(id);
        if (ad.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AdvertisementDTO advertisementDTO = toDTO(ad.get());
        return new ResponseEntity<>(advertisementDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Advertisement> addAdvertisements(@Valid @RequestBody Advertisement advertisement) {
        Set<ConstraintViolation<Advertisement>> violationsAd = validator.validate(advertisement);
        Set<ConstraintViolation<Customer>> violationsCustomer = validator.validate(advertisement.getCustomer());
        if (!violationsAd.isEmpty() || !violationsCustomer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Advertisement newAdvertisement = advertisementRepository.save(advertisement);
        return new ResponseEntity<>(newAdvertisement, HttpStatus.CREATED);
    }

    @DeleteMapping("request/{id}")
    public ResponseEntity<Volunteer> deleteAdvertisements(@PathVariable("id") String id) {
        if (advertisementRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        advertisementRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private AdvertisementDTO toDTO(Advertisement advertisement) {
        return new AdvertisementDTO(advertisement.getId(),
                advertisement.getName(),
                advertisement.getCategory(),
                advertisement.getIsOpen(),
                advertisement.getCustomer(),
                advertisement.getDescription(),
                advertisement.getResponded());
    }
}
