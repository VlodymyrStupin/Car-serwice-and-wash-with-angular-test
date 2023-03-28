package com.stupin.carService.repository;

import com.stupin.carService.domain.dto.UserImplUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserImplUserDatailsRepository extends JpaRepository<UserImplUserDetails, Long> {
    Optional<UserImplUserDetails> findByEmail(String email);
}
