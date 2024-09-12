package com.crio.api.domain.address;

import java.util.UUID;

public record AddressResponseDTO(
    UUID id,
    String city,
    String uf
) {

}
