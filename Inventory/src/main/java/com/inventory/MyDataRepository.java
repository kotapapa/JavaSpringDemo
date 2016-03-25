package com.inventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyDataRepository
        extends JpaRepository<MyData, Integer> {

	public List<MyData> findByName(String name);

	public List<MyData> findByNameLike(String fstr);

}