package com.computec.computec.dao;

import com.computec.computec.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenDao extends JpaRepository<Orden, Integer> {
}
