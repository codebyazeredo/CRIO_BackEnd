package com.crio.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crio.api.domain.address.Address;
import com.crio.api.domain.address.AddressRequestDTO;
import com.crio.api.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    //Query
    @GetMapping("/city/{city}")
    public ResponseEntity<Optional<Address>> findByCity(@RequestParam String city) {
        Optional<Address> listAddress = addressService.findByCity(city);
        return ResponseEntity.ok(listAddress);
    }

    @GetMapping("/uf/{uf}")
    public ResponseEntity<Optional<Address>> findByUf(@RequestParam String uf) {
        Optional<Address> listAddress = addressService.findByUf(uf);
        return ResponseEntity.ok(listAddress);
    }

    //Crud
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Address> create(@ModelAttribute AddressRequestDTO dto) {
        Address newAddress = new Address();
        newAddress.setCity(dto.city());
        newAddress.setUf(dto.uf());

        Address savedAddress = addressService.createAddress(dto);
        return ResponseEntity.ok(savedAddress);
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") UUID id) {
        Optional<Address> address = addressService.getAddressById(id);
        return address.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable("id") UUID id, @RequestBody AddressRequestDTO addressRequestDTO) {
        Address updatedAddress = addressService.updateAddress(id, addressRequestDTO);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") UUID id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
