package com.example.jokenpo.repositories;

import com.example.jokenpo.models.JokenpoMatchModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokenpoMatchRepository extends JpaRepository<JokenpoMatchModel, Long> {
    // This interface that will be used to access the database which provides the basic CRUD operations
}
