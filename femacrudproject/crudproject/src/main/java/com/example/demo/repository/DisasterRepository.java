package com.example.demo.repository;

import com.example.demo.entity.Disaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisasterRepository extends CrudRepository<Disaster, Long> {
}
