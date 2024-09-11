package com.crio.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.api.domain.Invite.Invite;
import com.crio.api.domain.Invite.InviteRequestDTO;
import com.crio.api.repository.InviteRepository;

@Service
public class InviteService {

    @Autowired
    private InviteRepository inviteRepository;

    public Invite createInvite(InviteRequestDTO irdto){
        
        Invite newInvite = new Invite();
        newInvite.setConfirmation(irdto.confirmation());

        inviteRepository.save(newInvite);
        return newInvite;
    }

    public List<Invite> getAllInvite(){
        return inviteRepository.findAll();
    }

    public Optional<Invite> getInviteById(UUID id){
        return Optional.of(inviteRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found")));
    }

    public Invite updaInvite(UUID id, InviteRequestDTO irdto){
        Invite updatedInvite = getInviteById(id).orElseThrow();

        updatedInvite.setConfirmation(irdto.confirmation());
        return inviteRepository.save(updatedInvite);
    }

    public void deleteInvite(UUID id){
        inviteRepository.deleteById(id);
    }

}
