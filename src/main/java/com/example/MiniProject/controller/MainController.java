package com.example.MiniProject.controller;


import com.example.MiniProject.domain.Item;
import com.example.MiniProject.service.ItemService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    ItemService itemService;

    @GetMapping("/")
    public ModelAndView HomePage(){
        ModelAndView mav = new ModelAndView();
        List<Item> items = itemService.findAll();
        mav.addObject("listItems", items);
        mav.setViewName("index");
        return mav;
    }

    @GetMapping("/showItemForm")
    public ModelAndView FormItem(){
        ModelAndView mav = new ModelAndView();
        Item item = new Item();

        mav.addObject("item",item);
        mav.setViewName("new_item");


        return mav;
    }

    @PostMapping("/saveItem")
    public String saveItem(@ModelAttribute("item") Item item){
        itemService.saveItem(item);

        return "redirect:/";
    }

    @GetMapping("/editItem")
    ModelAndView EditItem(@Valid @RequestParam("itemID") Integer item){
        ModelAndView mav = new ModelAndView();
        mav.addObject("item",item);
        Item tmp = itemService.finOne(item);


        mav.addObject("item",tmp);
        mav.setViewName("edit_item");
        return mav;
    }



    @GetMapping("/searchItem")
    public ModelAndView SearchItem(@RequestParam("keyword") String keyword, @RequestParam("param") String param){
        ModelAndView mav = new ModelAndView();
        List<Item> items = new ArrayList<>();

        switch (param){
            case "0":
                items = itemService.finByName(keyword);
                break;
            case "1":
                items = itemService.finByType(keyword);
                break;
            case "2":
                items = itemService.finByLocation(keyword);
                break;
        }
        if(keyword.equals("")){
            items = itemService.findAll();
        }
        mav.addObject("listItems", items);

        mav.setViewName("index");
        return mav;
    }

    @GetMapping("/updateItem")
    public ModelAndView searchItem(@ModelAttribute  Item item) {
        ModelAndView mav = new ModelAndView();
        itemService.update(item);

        List<Item> items = itemService.findAll();
        mav.addObject("listItems", items);
        mav.setViewName("index");

        return mav;
    }

    @GetMapping("/deleteItem")
    public ModelAndView deleteItem(@ModelAttribute  Item item) {
        ModelAndView mav = new ModelAndView();
        System.out.println(item.getC_id().toString());
        itemService.delete(item.getC_id());
        List<Item> items = itemService.findAll();
        mav.addObject("listItems", items);
        mav.setViewName("index");
        return mav;
    }
}
