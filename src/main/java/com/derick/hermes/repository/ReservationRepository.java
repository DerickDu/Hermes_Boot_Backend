package com.derick.hermes.repository;

import com.derick.hermes.model.Reservation;
import com.derick.hermes.model.Stay;
import com.derick.hermes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByGuest(User guest);

    List<Reservation> findByStay(Stay stay);

    Reservation findByIdAndGuest(Long id, User guest); // for deletion

    List<Reservation> findByStayAndCheckoutDateAfter(Stay stay, LocalDate date);

}

