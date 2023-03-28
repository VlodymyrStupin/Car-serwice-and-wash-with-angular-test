package com.stupin.carService.repository;

import com.stupin.carService.domain.dao.BookingDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingDao, Integer> {

}
