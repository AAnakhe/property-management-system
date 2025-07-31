package com.codingplayground.propertyrental.repository;

import com.codingplayground.propertyrental.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByLocationContainingAndPriceLessThanEqual(String location, double price);
}
