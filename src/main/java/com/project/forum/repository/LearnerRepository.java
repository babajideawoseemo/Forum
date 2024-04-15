package com.project.forum.repository;

import com.project.forum.domain.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface LearnerRepository extends JpaRepository<Learner, String> {

    Optional<Learner> findOneByEmailAndPassword(String email, String password);

    Learner findByEmail(String email);
}
