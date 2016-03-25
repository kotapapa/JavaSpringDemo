package com.inventory.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.inventory.domain.Items;

public interface ItemsRepository extends MongoRepository<Items, String> {

  Items findByItemCode(String itemCode);

  Iterable<Items> findByItemNameLike(String itemName);

}
