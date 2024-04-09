package com.arrow.budgettracker.msbudgettracker.Item.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.math.BigDecimal;

@Converter
public class MoneyConverter implements AttributeConverter<BigDecimal, String> {

    @Override
    public String convertToDatabaseColumn(BigDecimal attribute) {
        // Add your logic to convert BigDecimal to the database representation
        return "RM" + attribute.toString();
    }

    @Override
    public BigDecimal convertToEntityAttribute(String dbData) {
        // Add your logic to convert the database representation to BigDecimal
        return new BigDecimal(dbData.replace("RM", ""));
    }
}

