package com.velomagaz.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.velomagaz.app.entity.*;

@Repository
public interface IProductComponentRepository extends JpaRepository<ProductComponent, Integer>{
	List<ProductComponent> findByProduct(Product product);
}
