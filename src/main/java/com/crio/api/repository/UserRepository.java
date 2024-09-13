package com.crio.api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crio.api.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    
    @Query("SELECT u FROM User u")
    List<User> findAllUser();

    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findByIdUser(UUID id);

    @Modifying
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.password = :password, u.type = :type, u.createdAt = :createdAt, u.updatedAt = :updatedAt WHERE u.id = :id")
    void updateUser(
        @Param("id") UUID id,
        @Param("name") String name,
        @Param("email") String email,
        @Param("password") String password,
        @Param("type") Integer type,
        @Param("createdAt") LocalDateTime createdAt,
        @Param("updatedAt") LocalDateTime updatedAt
    );
    
    @Query("DELETE FROM User u WHERE u.id = :id")
    void deleteByIdUser(UUID id);
}
