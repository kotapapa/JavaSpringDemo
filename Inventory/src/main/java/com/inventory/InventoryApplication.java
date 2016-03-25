package com.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.inventory.viewhelper.MyDialect;

@SpringBootApplication
public class InventoryApplication implements CommandLineRunner {

@Autowired
private MyMongoRepository mongoRepository;

public static void main(String[] args) {
	SpringApplication.run(InventoryApplication.class, args);
}

//THYMELEAF Utility Object
@Bean
MyDialect myDialect(){
	return new MyDialect();
}

@Override
public void run(String... args) throws Exception {
//	mongoRepository.deleteAll();
//	mongoRepository.save(new MongoData("helo","this is sample memo."));
//	mongoRepository.save(new MongoData("check!","check sample code..."));
//	mongoRepository.save(new MongoData("サンプルメモ","これはサンプルのメモです。"));
}
}
