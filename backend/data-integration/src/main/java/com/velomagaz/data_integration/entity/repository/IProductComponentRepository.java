package com.velomagaz.data_integration.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.velomagaz.data_integration.entity.*;

@Repository
public interface IProductComponentRepository extends JpaRepository<ProductComponent, Integer>{
	List<ProductComponent> findByProduct(Product product);
}
