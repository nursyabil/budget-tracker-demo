package com.arrow.budgettracker.msbudgettracker.Item.repository;

import com.arrow.budgettracker.msbudgettracker.Item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
}
