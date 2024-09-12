package com.crio.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.api.domain.address.Address;
import com.crio.api.domain.address.AddressRequestDTO; 
import com.crio.api.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody AddressRequestDTO addressRequestDTO) {
        Address newAddress = addressService.createAddress(addressRequestDTO);
        return ResponseEntity.ok(newAddress);
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
