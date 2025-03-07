package com.velomagaz.data_integration.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.velomagaz.data_integration.entity.*;


@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer>{

}
