package com.inventory.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inventory.domain.Inventorylogs;
import com.inventory.domain.Items;
import com.inventory.service.impl.InventorylogsService;
import com.inventory.service.impl.ItemsService;
import com.inventory.utils.JsonLoader;

@Controller
@RequestMapping(value = "/inventorylogs")
public class InventorylogsController extends BaseController {
  private static Logger logger = LoggerFactory.getLogger(InventorylogsController.class);

  private static final int PAGE_SIZE = 30;

  @Autowired
  private ItemsService itemsService;

  @Autowired
  private InventorylogsService inventorylogsService;


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
    logger.debug("InventorylogsController:[index] Passing through...");

    Iterable<Inventorylogs> result = inventorylogsService.findAll(pageNo, PAGE_SIZE, "orderNumber");
    model.addAttribute("result", result);

    int totalCount = (int)inventorylogsService.count();

    addPageAttr(inventorylogsService.calcPage(totalCount, pageNo, PAGE_SIZE), model);

    return "Inventorylogs/index";
  }


  @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
  public String detail(
      @PathVariable(value="id") String id,
      Model model) {
    logger.debug("InventorylogsController:[detail] Passing through...");

    Inventorylogs inventorylog = inventorylogsService.findById(id);

    String json = "{}";
    if (inventorylog != null) {
      json = JsonLoader.toJson(inventorylog);
    }

    Items item = itemsService.findByPk(inventorylog.getItemCode());

    model.addAttribute("order", inventorylog);
    model.addAttribute("item", item);
    model.addAttribute("json", json);

    return "Inventorylogs/detail";
  }

}
