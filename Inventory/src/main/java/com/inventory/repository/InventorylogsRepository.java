package com.inventory.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.inventory.domain.Inventorylogs;

public interface InventorylogsRepository extends MongoRepository<Inventorylogs, String> {

	Inventorylogs findByInvlogNumber(Long invlogNumber);

	Iterable<Inventorylogs> findByInvlogDate(Date invlogDate);

	Iterable<Inventorylogs> findByUserId(String userId);

	Iterable<Inventorylogs> findByItemCode(String itemCode);

}
