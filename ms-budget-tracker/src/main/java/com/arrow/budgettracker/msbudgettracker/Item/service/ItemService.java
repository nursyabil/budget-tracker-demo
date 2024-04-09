package com.arrow.budgettracker.msbudgettracker.Item.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ItemService {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy");

    public String formatAndSaveLocalDateTime(LocalDateTime localDateTime) {
        return formatLocalDateTime(localDateTime);
    }

    private String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_FORMATTER);
    }
}
