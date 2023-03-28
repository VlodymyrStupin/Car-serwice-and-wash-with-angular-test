package com.stupin.carService.service.impl;

import com.stupin.carService.converter.BookingConverter;
import com.stupin.carService.domain.dao.BookingDao;
import com.stupin.carService.domain.dto.Booking;
import com.stupin.carService.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static com.stupin.carService.converter.UserConverter.toDao;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    //    public List<Booking> findAll();
//
//    public AdminDao findByDate(Date date);
//
//    public List<AdminDao> findAll();
//
    public Booking save(Booking booking) {
        BookingDao dao;
        if (booking.getId() != null && bookingRepository.existsById(booking.getId())) {
            dao = bookingRepository.findById(booking.getId()).get();
            dao.setService(booking.getService());
            dao.setEmail(booking.getEmail());
            dao.setDate(booking.getDate());
            dao.setDescription(booking.getDescription());
            dao.setUserDao(toDao(booking.getUser()));
        } else {
            dao = BookingConverter.bookingToDao(booking);
        }
        return BookingConverter.bookingToDto(bookingRepository.save(dao));
    }

}
