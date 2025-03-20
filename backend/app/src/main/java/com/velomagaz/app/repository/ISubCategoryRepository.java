package com.velomagaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.velomagaz.app.entity.SubCategory;

@Repository
public interface ISubCategoryRepository extends JpaRepository<SubCategory, Integer>{
	public SubCategory findBySubcategoryName(String name);
}
