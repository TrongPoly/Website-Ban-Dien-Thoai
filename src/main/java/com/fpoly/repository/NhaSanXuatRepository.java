package com.fpoly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.model.NhaSanXuat;

public interface NhaSanXuatRepository extends JpaRepository<NhaSanXuat, Integer>{
	@Query("select o from NhaSanXuat o where o.id like ?1")
	List<NhaSanXuat> findbykeywords(String key);
		
}
