package com.velomagaz.data_integration.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.velomagaz.data_integration.entity.*;

import jakarta.transaction.Transactional;


@Repository
public interface IProductRepository extends JpaRepository<Product, String>{
	   @Modifying
	   @Transactional
	   @Query("UPDATE Product p SET p.image = :image WHERE p.id = :id")
	   int updateImageById(@Param("id") String id, @Param("image") byte[] image);
}
