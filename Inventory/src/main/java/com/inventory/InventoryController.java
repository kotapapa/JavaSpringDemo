package com.inventory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InventoryController {

    @Autowired
    private MyMongoRepository mongoRepository;

    @RequestMapping("/helo")
    public Model helo(Model model) {
        List<MongoData> datas = mongoRepository.findAll();
        model.addAttribute("msg","this is MongoDB sample.");
        model.addAttribute("datas",datas);
        return model;
    }

    @RequestMapping(value = "/helo", method = RequestMethod.POST)
    public Model post(Model model, @RequestParam("key") String key) {
        List<MongoData> datas = mongoRepository.findByTitle(key);
        if (datas == null)
            { datas = new ArrayList<MongoData>(); }
        model.addAttribute("msg","this is MongoDB sample.");
        model.addAttribute("datas",datas);
        return model;
    }

    @RequestMapping(value="/post", method=RequestMethod.POST)
    public String helo(Model model,
            @RequestParam("title") String title,
            @RequestParam("memo") String memo){
    	MongoData mydata = new MongoData(title,memo);
        mongoRepository.save(mydata);
        List<MongoData> list = mongoRepository.findAll();
        model.addAttribute("datas",list);
        return "helo";
    }
}

class Data {
    private String name;
    private String mail;
    private String tel;

    public Data(String name, String mail, String tel){
        this.name = name;
        this.mail = mail;
        this.tel = tel;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getMail() { return mail; }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
}
