package com.crio.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crio.api.domain.address.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    @Query("SELECT a FROM Address a WHERE a.city = :city")
    Optional<Address> findByCity(String city);

    @Query("SELECT a FROM Address a WHERE a.uf = :uf")
    Optional<Address> findByUf(String uf);
}
