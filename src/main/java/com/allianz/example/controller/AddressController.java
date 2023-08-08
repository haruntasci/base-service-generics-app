package com.allianz.example.controller;

import com.allianz.example.model.AddressDTO;
import com.allianz.example.model.requestDTO.AddressRequestDTO;
import com.allianz.example.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {
    public static String SUCCESS_MESSAGE = "Delete success";
    public static String ERROR_MESSAGE = "Delete error";


    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(AddressRequestDTO addressRequestDTO) {
        return new ResponseEntity<>(addressService.createDTO(addressRequestDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        return new ResponseEntity<>(addressService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AddressDTO> getAddressByUUID(@PathVariable UUID uuid) {
        AddressDTO addressDTO = addressService.getByUUID(uuid);
        if (addressDTO != null) {
            return new ResponseEntity<>(addressDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteAddressByUUID(@PathVariable UUID uuid) {
        if (addressService.deleteByUUID(uuid) == 1) {
            return new ResponseEntity<>(SUCCESS_MESSAGE, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ERROR_MESSAGE, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


}
