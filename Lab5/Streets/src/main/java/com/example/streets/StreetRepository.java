package com.example.streets;


import com.example.models.entities.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {
    List<Street> getAllByName(String name);
}
