package com.fpoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.model.DiaChi;

public interface DiaChiRepository extends JpaRepository<DiaChi, Integer>{

	void deleteById(DiaChi diachi);

}
