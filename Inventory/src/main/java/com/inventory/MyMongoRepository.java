package com.inventory;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyMongoRepository
extends MongoRepository<MongoData, String>{

    public List<MongoData> findByTitle(String title);
}
