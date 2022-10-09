package com.derick.hermes.repository;

import com.derick.hermes.model.StayReservedDate;
import com.derick.hermes.model.StayReservedDateKey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface StayReservationDateRepository extends JpaRepository<StayReservedDate, StayReservedDateKey> {
    @Query(value = "SELECT srd.id.stay_id FROM StayReservedDate srd WHERE srd.id.stay_id IN ?1 AND srd.id.date BETWEEN ?2 AND ?3 GROUP BY srd.id.stay_id")
    Set<Long> findByIdInAndDateBetween(List<Long> stayIds, LocalDate startDate, LocalDate endDate);
}

