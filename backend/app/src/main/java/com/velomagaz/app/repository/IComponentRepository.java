package com.velomagaz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.velomagaz.app.entity.*;

@Repository
public interface IComponentRepository extends JpaRepository<Component, Integer>{

}
