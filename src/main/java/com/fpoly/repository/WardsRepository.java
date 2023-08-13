package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.Ward;

public interface WardsRepository extends JpaRepository<Ward,Integer>{

}
