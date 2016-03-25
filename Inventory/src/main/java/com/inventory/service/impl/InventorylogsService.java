package com.inventory.service.impl;

import java.util.Date;
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

import com.inventory.domain.Inventorylogs;
import com.inventory.repository.InventorylogsRepository;
import com.inventory.service.AbstractService;

@Service
public class InventorylogsService extends AbstractService<Inventorylogs> {
  private static Logger logger = LoggerFactory.getLogger(InventorylogsService.class);

  @Autowired
  private InventorylogsRepository invlogsRepository;

  @Override
  protected MongoRepository<Inventorylogs, String> getRepository() {
    return invlogsRepository;
  }

  @Override
  public Inventorylogs findByPk(Object...keys) {
    return invlogsRepository.findByInvlogNumber((Long)keys[0]);
  }

  @Override
  public Iterable<Inventorylogs> findByNameLike(String name, String sortColumn) {
    return null;
  }

  @Override
  public long searchCount(String keyword) {
    return 0;
  }

  @Override
  public Iterable<Inventorylogs> search(String keyword, int page, int size, String sort) {
	return null;
  }

  public Iterable<Inventorylogs> findByDate(Date invlogDate) {
	return invlogsRepository.findByInvlogDate(invlogDate);
  }

  public Iterable<Inventorylogs> findByUserId(String userId) {
	return invlogsRepository.findByUserId(userId);
  }

  @Override
  public long count(Inventorylogs searchCondition) {
    Criteria criteria = makeCriteria(searchCondition);
    Query query= makeQuery(criteria);
    return doCount(query, Inventorylogs.class);
  }

  @Override
  public List<Inventorylogs> search(int page, int size, Sort sort, Inventorylogs searchCondition) {
    Criteria criteria = makeCriteria(searchCondition);
    Query query= makeQuery(criteria);
    query.skip(calcSkipNum(page, size)).limit(size);
    if (sort != null) {
      query.with(sort);
    }
    return doFind(query, Inventorylogs.class);
  }

  @Override
  protected Criteria makeCriteriaByPk(Inventorylogs model) {
    return Criteria.where("invlogNumber").is(model.getInvlogNumber());
  }

  @Override
  protected Criteria makeCriteria(Inventorylogs model) {
    Criteria criteria = null;
    if (model.getInvlogNumber() != null && model.getInvlogNumber() > 0L) {
      criteria = makeCriteria(criteria, "invlogNumber", model.getInvlogNumber());
    }
    if (StringUtils.isNotEmpty(model.getUserId())) {
      criteria = makeCriteria(criteria, "userId", model.getUserId());
    }
    return criteria;
  }

  @Override
  protected Update makeAllUpdate(Inventorylogs model) {
    Update update = new Update();
    update.set("invlogNumber", model.getInvlogNumber());
    if (model.getInvlogDate() == null) {
      update.unset("invlogDate");
    } else {
      update.set("invlogDate", model.getInvlogDate());
    }
    if (model.getInvlogStatus() == null) {
      update.unset("invlogStatus");
    } else {
      update.set("invlogStatus", model.getInvlogStatus());
    }
    update.set("quantityInput", model.getQuantityInput());
    update.set("quantityOutput", model.getQuantityOutput());
    update.set("quantityStock", model.getQuantityStock());
    update.set("userId", model.getUserId());
    update.set("status", model.getStatus());
    update.set("comments", model.getComments());
    return update;
  }

}
