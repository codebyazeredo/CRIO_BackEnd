package com.crio.api.domain.Invite;

import java.util.UUID;

public record InviteResponseDTO( 
        UUID id,
        Boolean confirmation) {
   
}
