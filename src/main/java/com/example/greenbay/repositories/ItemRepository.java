package com.example.greenbay.repositories;

import com.example.greenbay.models.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {


  @Query(value = "SELECT i FROM Item i WHERE i.sellable = true")
  Page<Item> findSellableItemsByPage(Pageable pageable);

}
