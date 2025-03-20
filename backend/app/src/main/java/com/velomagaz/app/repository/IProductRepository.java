package com.velomagaz.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.velomagaz.app.entity.*;
import org.springframework.data.domain.*;


@Repository
public interface IProductRepository extends JpaRepository<Product, String>{
	@Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%'))")
	public Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);
    
    @Query("SELECT p FROM Product p WHERE p.id LIKE CONCAT('%', :id, '%')")
    public Page<Product> findByIdContaining(@Param("id") String id, Pageable pageable);
    
    public Page<Product> findBySubcategory_SubcategoryName(String subcategoryName, Pageable pageable);
}
