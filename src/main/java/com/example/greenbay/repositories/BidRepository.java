package com.example.greenbay.repositories;

import com.example.greenbay.models.Bid;
import org.springframework.data.repository.CrudRepository;

public interface BidRepository extends CrudRepository<Bid, Long> {
}
