package com.crio.api.domain.address;

public record AddressRequestDTO (
    String city,
    String uf
) {

    public String getCity() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
