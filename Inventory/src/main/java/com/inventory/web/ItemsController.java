package com.inventory.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.domain.Items;
import com.inventory.service.impl.ItemsService;
import com.inventory.utils.JsonLoader;

@Controller
@RequestMapping(value = "/items")
public class ItemsController extends BaseController {
  private static Logger logger = LoggerFactory.getLogger(ItemsController.class);

  private static final int PAGE_SIZE = 10;

  @Autowired
  private ItemsService itemsService;

  @RequestMapping(method = RequestMethod.GET)
  public String _index(Model model) {
    return index(1, model);
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(Model model) {
    return index(1, model);
  }

  @RequestMapping(value = "/{pageNo}", method = RequestMethod.GET)
  public String index(
      @PathVariable() Integer pageNo,
      Model model) {
    logger.debug("ItemsController:[index] Passing through...");

    Iterable<Items> result = itemsService.findAll(pageNo, PAGE_SIZE, "itemCode");
    model.addAttribute("result", result);

    int totalCount = (int)itemsService.count();

    addPageAttr(itemsService.calcPage(totalCount, pageNo, PAGE_SIZE), model);

    return "Items/index";
  }

  @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
  public String detail(
      @PathVariable(value="id") String id,
      Model model) {
    logger.debug("ItemsController:[detail] Passing through...");

    Items item = itemsService.findById(id);

    String json = "{}";
    if (item != null) {
      json = JsonLoader.toJson(item);
    }

    model.addAttribute("item", item);
    model.addAttribute("json", json);

    return "Items/detail";
  }

  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public String search(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String maker,
      Model model) {
    logger.debug("ItemsController:[search] Passing through...");

    int pageNo = 1;

    Items searchCondition = new Items();
    if (StringUtils.isNotEmpty(name)) {
      searchCondition.setItemName(name);
    }
    if (StringUtils.isNotEmpty(maker)) {
      searchCondition.setItemMaker(maker);
    }

    List<Items> result = itemsService.search(pageNo, PAGE_SIZE, null, searchCondition);
    if (result != null) {
      logger.info("result found:{}", result.size());
    } else {
      logger.info("result not found");
    }

    int totalCount = (int)itemsService.count(searchCondition);
    logger.info("totalCount:{}", totalCount);

    model.addAttribute("result", result);

    addPageAttr(itemsService.calcPage(totalCount, pageNo, PAGE_SIZE), model);

    return "Items/index";
  }

}
