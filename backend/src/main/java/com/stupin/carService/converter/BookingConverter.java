package com.stupin.carService.converter;

import com.stupin.carService.domain.dao.BookingDao;
import com.stupin.carService.domain.dto.Booking;

import static com.stupin.carService.converter.UserConverter.toDao;
import static com.stupin.carService.converter.UserConverter.toDto;

public class BookingConverter {
    public static Booking bookingToDto(BookingDao bookingDao) {
        return new Booking(
                bookingDao.getId(),
                bookingDao.getDate(),
                bookingDao.getDescription(),
                bookingDao.getService(),
                bookingDao.getEmail(),
                toDto(bookingDao.getUserDao()));

    }

    public static BookingDao bookingToDao(Booking booking) {
        return new BookingDao(
                booking.getId(),
                booking.getDate(),
                booking.getDescription(),
                booking.getService(),
                booking.getEmail(),
                toDao(booking.getUser()));
    }
}
