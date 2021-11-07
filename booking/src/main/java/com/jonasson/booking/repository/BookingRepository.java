package com.jonasson.booking.repository;
import com.jonasson.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    /**
     *
     * @param from Date from car is booked
     * @param to Date to car is booked
     * @param Carid CarId of car to check if booked
     * @return A empty list if car is not booked during from and to dates. If car is booked the list size should be >= 1
     */
    @Query("FROM Booking b where (b.carId = ?3 AND b.fromDate BETWEEN ?1 AND ?2) OR" +
            " (b.toDate BETWEEN ?1 AND ?2 AND b.carId = ?3) OR (?1 BETWEEN b.fromDate AND b.toDate) OR (?2 BETWEEN b.fromDate AND b.toDate)")
    List<Booking> checkIfCarIsBooked(Date from, Date to, Long Carid);
    @Query("SELECT b.carId FROM Booking b where (b.fromDate BETWEEN ?1 AND ?2) OR (b.toDate BETWEEN ?1 AND ?2) OR (?1 BETWEEN b.fromDate AND b.toDate) OR (?2 BETWEEN b.fromDate AND b.toDate)")
    List<Long> getAllCarIdsBookedBetweenDates(Date from, Date to);
}
