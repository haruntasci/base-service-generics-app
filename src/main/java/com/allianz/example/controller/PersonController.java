package com.allianz.example.controller;

import com.allianz.example.model.PersonDTO;
import com.allianz.example.model.requestDTO.AddressRequestDTO;
import com.allianz.example.model.requestDTO.PersonRequestDTO;
import com.allianz.example.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("person")
public class PersonController {

    public static String SUCCESS_MESSAGE = "Delete success";
    public static String ERROR_MESSAGE = "Delete error";

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(PersonRequestDTO personRequestDTO) {
        return new ResponseEntity<>(personService.createDTO(personRequestDTO), HttpStatus.OK);
    }

    @PutMapping("/{addressUUID}/{personUUID}")
    public ResponseEntity<PersonDTO> addAddressToPerson(@PathVariable UUID addressUUID,
                                                        @PathVariable UUID personUUID) {
        return new ResponseEntity<>(personService.addAddressToPerson(addressUUID, personUUID), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllAddresses() {
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PersonDTO> getAddressByUUID(@PathVariable UUID uuid) {
        PersonDTO personDTO = personService.getByUUID(uuid);
        if (personDTO != null) {
            return new ResponseEntity<>(personDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteAddressByUUID(@PathVariable UUID uuid) {
        if (personService.deleteByUUID(uuid) == 1) {
            return new ResponseEntity<>(SUCCESS_MESSAGE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


}