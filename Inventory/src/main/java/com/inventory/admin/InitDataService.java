package com.inventory.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.inventory.domain.Inventorylogs;
import com.inventory.domain.Items;
import com.inventory.repository.InventorylogsRepository;
import com.inventory.repository.ItemsRepository;
import com.inventory.utils.JsonLoader;
import com.inventory.utils.MongoService;

@Service
public class InitDataService {
  private static Logger logger = LoggerFactory.getLogger(InitDataService.class);

  private static final String RESOURCE_DIR = "classpath:data/init";

  private static final String CUSTOMERS = RESOURCE_DIR + "/Customers/Customers.json";
  private static final String ORDERDETAILS = RESOURCE_DIR + "/OrderDetails/OrderDetails.json";
  private static final String ORDERS = RESOURCE_DIR + "/Inventorylogs/Inventorylogs.json";
  private static final String PAYMENTS = RESOURCE_DIR + "/Payments/Payments.json";
  private static final String PRODUCTLINES = RESOURCE_DIR + "/ProductLines/ProductLines.json";
  private static final String PRODUCTS = RESOURCE_DIR + "/Items/Items.json";

  @Autowired
  private MongoService mongoService;

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private InventorylogsRepository inventorylogsRepository;

  @Autowired
  private ItemsRepository itemsRepository;

  public Map<String ,Integer> init() {
    logger.info("init start");

    mongoService.collectionAllDrop();

    List<Inventorylogs> inventorylogsList =
      JsonLoader.multi(ORDERS, Inventorylogs.class);
    if (CollectionUtils.isNotEmpty(inventorylogsList)) {
      logger.info("inventorylogsList: " + inventorylogsList.size());
    } else {
      logger.info("inventorylogsList null");
    }

    List<Items> itemsList =
      JsonLoader.multi(PRODUCTS, Items.class);
    if (CollectionUtils.isNotEmpty(itemsList)) {
      logger.info("itemsList: " + itemsList.size());
    } else {
      logger.info("itemsList null");
    }

    int iv = inventorylogsRepository.save(inventorylogsList).size();
    int it = itemsRepository.save(itemsList).size();

    Map<String ,Integer> result = new HashMap<>();
    result.put("inventorylogs", iv);
    result.put("items", it);

    logger.info("init end");

    return result;
  }

}
