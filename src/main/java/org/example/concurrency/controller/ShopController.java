package org.example.concurrency.controller;

import org.example.concurrency.dao.ShopTable;
import org.example.concurrency.dao.Student;
import org.example.concurrency.service.ShopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/buy/{id}")
    public ShopTable buy1(@PathVariable("id") Integer id) {
        return shopService.getBookAndSave(id);
    }

    @GetMapping("/buy2/{id}")
    public ShopTable buy2(@PathVariable("id") Integer id) {
        return shopService.getBookAndSave(id);
    }
}
