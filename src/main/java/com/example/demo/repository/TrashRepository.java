package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Trash;

public interface TrashRepository extends JpaRepository<Trash, Long> {

    
}
