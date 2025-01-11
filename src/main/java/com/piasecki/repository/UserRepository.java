package com.piasecki.repository;

import com.piasecki.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    void deleteById(Long id);

    @Query("SELECT u.id FROM User u") List<Long> findAllIds();

//    User findUserByUsername(String username);
}
