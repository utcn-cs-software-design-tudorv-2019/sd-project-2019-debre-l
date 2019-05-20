package com.sec.repository;

import org.springframework.data.repository.CrudRepository;

import com.sec.entity.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Long> {

}
