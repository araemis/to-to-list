package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Checked;

public interface CheckedRepository extends JpaRepository<Checked, Long> {

}
