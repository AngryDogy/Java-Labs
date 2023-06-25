package com.example.lab3_spring.streets;

import com.example.lab3_spring.hibernate.entities.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {
    List<Street> getAllByName(String name);

}
