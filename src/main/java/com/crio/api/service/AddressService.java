package com.crio.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.api.domain.address.Address;
import com.crio.api.domain.address.AddressRequestDTO;
import com.crio.api.repository.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(AddressRequestDTO aDTO) {
        
        Address newAddress = new Address();
        newAddress.setCity(aDTO.city());
        newAddress.setUf(aDTO.uf());

        addressRepository.save(newAddress);
        return newAddress;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(UUID id) {
        return Optional.of(addressRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public Address updateAddress(UUID id, AddressRequestDTO aDTO) {
        Address updatedAddress = getAddressById(id).orElseThrow();
        
        updatedAddress.setCity(aDTO.city());
        updatedAddress.setUf(aDTO.uf());

        return addressRepository.save(updatedAddress);
    }

    public void deleteAddress(UUID id) {
        addressRepository.deleteById(id);
    }
}
