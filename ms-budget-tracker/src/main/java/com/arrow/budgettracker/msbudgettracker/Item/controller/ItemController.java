package com.arrow.budgettracker.msbudgettracker.Item.controller;

import com.arrow.budgettracker.msbudgettracker.Item.entity.Item;
import com.arrow.budgettracker.msbudgettracker.Item.repository.ItemRepository;
import com.arrow.budgettracker.msbudgettracker.Item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/v1/budget-tracker")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "Fiefi");
        return "hello";
    }

    @PostMapping("/create-item")
    public ResponseEntity<String> createItem(@RequestBody Item newItem){
        try {
            newItem.setPurchaseDate(LocalDateTime.now());
            Item createdItem = itemRepository.save(newItem);
            return new ResponseEntity<>("Entity created with ID: " + createdItem.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating entity: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/list-item")
    public String listItem(Model model) {
        List<Item> entities = itemRepository.findAll();
        String formattedDate = itemService.formatAndSaveLocalDateTime(entities.get(0).getPurchaseDate());
        for (Item entity: entities){
            formattedDate = itemService.formatAndSaveLocalDateTime(entity.getPurchaseDate());
        }
        BigDecimal total = entities.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        String totalAmount = String.valueOf(total);

        model.addAttribute("total", totalAmount);
        model.addAttribute("entities", entities);
        model.addAttribute("formattedDate", formattedDate);
        return "list-item";
    }
}
