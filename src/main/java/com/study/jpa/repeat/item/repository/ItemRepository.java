package com.study.jpa.repeat.item.repository;

import com.study.jpa.repeat.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
