package com.crio.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crio.api.domain.Invite.Invite;

@Repository
public interface InviteRepository extends JpaRepository<Invite, UUID>{
    
}
