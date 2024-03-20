package com.telmore.pcftester.components.redis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telmore.pcftester.components.redis.dao.ItemDao;
import com.telmore.pcftester.components.redis.domain.Item;

@RestController
@RequestMapping("/redis/")
@Conditional(RedisConditional.class)
public class RedisController {

    @Autowired
    public ItemDao itemDAO;

    @GetMapping("test")
    public String redis() {

        System.out.println("------- Start: example of using Redis as data store -------");
        Item item1 = new Item(1, "ItemOne", 30);
        itemDAO.addItem(item1);
        Item item2 = new Item(2, "ItemTwo", 35);
        itemDAO.addItem(item2);
        System.out.println("Number of Items: " + itemDAO.getNumberOfItems());
        System.out.println(itemDAO.getItemAtIndex(1));
        itemDAO.removeItem(item2);
        System.out.println("This should be null: " + itemDAO.getItemAtIndex(1)); //It will return null, because value is deleted.
        System.out.println("This should be ItemOne:  " + itemDAO.getItemAtIndex(0).getName());
        itemDAO.removeItem(item1);
        System.out.println("------- End: example of using Redis as data store -------");

        return "Redis successful - see application log";
    }

}
