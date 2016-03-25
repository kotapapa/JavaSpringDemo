package com.inventory.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.inventory.domain.Items;
import com.inventory.repository.ItemsRepository;
import com.inventory.service.AbstractService;

@Service
public class ItemsService extends AbstractService<Items> {
  private static Logger logger = LoggerFactory.getLogger(ItemsService.class);

  @Autowired
  private ItemsRepository itemsRepository;

  @Override
  protected MongoRepository<Items, String> getRepository() {
    return itemsRepository;
  }

  @Override
  public Items findByPk(Object... keys) {
    return itemsRepository.findByItemCode((String)keys[0]);
  }

  @Override
  public Iterable<Items> findByNameLike(String name, String sortColumn) {
    return itemsRepository.findByItemNameLike(name);
  }

  @Override
  public long searchCount(String keyword) {
    return 0;
  }

  @Override
  public Iterable<Items> search(String keyword, int page, int size, String sort) {
    return null;
  }

  @Override
  public long count(Items searchCondition) {
    Criteria criteria = makeCriteria(searchCondition);
    Query query= makeQuery(criteria);
    return doCount(query, Items.class);
  }

  @Override
  public List<Items> search(int page, int size, Sort sort, Items searchCondition) {
    Criteria criteria = makeCriteria(searchCondition);
    Query query= makeQuery(criteria);
    query.skip(calcSkipNum(page, size)).limit(size);
    if (sort != null) {
      query.with(sort);
    }
    return doFind(query, Items.class);
  }

  @Override
  protected Criteria makeCriteriaByPk(Items model) {
    return Criteria.where("itemCode").is(model.getItemCode());
  }

  @Override
  protected Criteria makeCriteria(Items model) {
    Criteria criteria = null;
    if (StringUtils.isNotEmpty(model.getItemCode())) {
      criteria = makeCriteria(criteria, "itemCode", model.getItemCode());
    }
    if (StringUtils.isNotEmpty(model.getItemName())) {
      criteria = makeCriteriaRegex(criteria, "itemName", model.getItemName());
    }
    if (StringUtils.isNotEmpty(model.getItemScale())) {
      criteria = makeCriteria(criteria, "itemScale", model.getItemScale());
    }
    if (StringUtils.isNotEmpty(model.getItemMaker())) {
      criteria = makeCriteria(criteria, "itemMaker", model.getItemMaker());
    }
    if (StringUtils.isNotEmpty(model.getItemDescription())) {
        criteria = makeCriteria(criteria, "itemDescription", model.getItemDescription());
    }
    if (model.getItemPrice() != null && model.getItemPrice().compareTo(BigDecimal.ZERO) > 0) {
        criteria = makeCriteria(criteria, "itemPrice", model.getItemPrice());
    }
    return criteria;
  }

  @Override
  protected Update makeAllUpdate(Items model) {
    Update update = new Update();
    update.set("itemName", model.getItemName());
    update.set("itemScale", model.getItemScale());
    update.set("itemMaker", model.getItemMaker());
    update.set("itemDescription",model.getItemDescription());
    update.set("itemPrice",model.getItemPrice());
    return update;
  }

}
