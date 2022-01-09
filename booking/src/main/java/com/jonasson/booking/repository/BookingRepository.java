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
    @Query("FROM Booking b where (b.carId = ?3 AND b.fromDate BETWEEN ?1 AND ?2 AND b.active = TRUE) OR" +
            " (b.toDate BETWEEN ?1 AND ?2 AND b.carId = ?3 AND b.active = TRUE) OR " +
            "(?1 BETWEEN b.fromDate AND b.toDate AND b.carId = ?3 AND b.active = TRUE) OR " +
            "(?2 BETWEEN b.fromDate AND b.toDate AND b.carId = ?3 AND b.active = TRUE)")
    List<Booking> checkIfCarIsBooked(Date from, Date to, Long Carid);
    @Query("SELECT b.carId FROM Booking b where (b.fromDate BETWEEN ?1 AND ?2) OR " +
            "(b.toDate BETWEEN ?1 AND ?2 AND b.active = TRUE) OR (?1 BETWEEN b.fromDate AND b.toDate) OR " +
            "(?2 BETWEEN b.fromDate AND b.toDate AND b.active = TRUE)")
    List<Long> getAllCarIdsBookedBetweenDates(Date from, Date to);
    @Query("FROM Booking b where b.customerId = ?1")
    List<Booking> getCustomersBookings(Long id);

    @Query("FROM Booking b where (b.carId = ?1 AND b.active = TRUE)")
    List<Booking> getActiveBookingsWithCar(Long id);

    @Query("FROM Booking b where (b.carId = ?3 AND b.fromDate BETWEEN ?1 AND ?2 AND b.active = TRUE AND b.id != ?4) OR" +
            " (b.toDate BETWEEN ?1 AND ?2 AND b.carId = ?3 AND b.active = TRUE AND b.id != ?4) OR " +
            "(?1 BETWEEN b.fromDate AND b.toDate AND b.carId = ?3 AND b.active = TRUE AND b.id != ?4) OR " +
            "(?2 BETWEEN b.fromDate AND b.toDate AND b.carId = ?3 AND b.active = TRUE AND b.id != ?4)")
    List<Booking> checkIfCarIsBookedUpdateBooking(Date from, Date to, Long Carid, Long bookingId);
}
