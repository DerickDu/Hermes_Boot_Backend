package com.derick.hermes.service;

import com.derick.hermes.model.Stay;
import com.derick.hermes.repository.LocationRepository;
import com.derick.hermes.repository.StayRepository;
import com.derick.hermes.repository.StayReservationDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SearchService {
    private StayRepository stayRepository;
    private StayReservationDateRepository stayReservationDateRepository;
    private LocationRepository locationRepository;

    @Autowired
    public SearchService(StayRepository stayRepository, StayReservationDateRepository stayReservationDateRepository, LocationRepository locationRepository) {
        this.stayRepository = stayRepository;
        this.stayReservationDateRepository = stayReservationDateRepository;
        this.locationRepository = locationRepository;
    }

    public List<Stay> search(int guestNumber, LocalDate checkinDate, LocalDate checkoutDate, double lat, double lon, String distance) {

        List<Long> stayIds = locationRepository.searchByDistance(lat, lon, distance);
        if (stayIds == null || stayIds.isEmpty()) {
            return new ArrayList<>();
        }

        Set<Long> reservedStayIds = stayReservationDateRepository.findByIdInAndDateBetween(stayIds, checkinDate, checkoutDate.minusDays(1));

        List<Long> filteredStayIds = new ArrayList<>();
        for (Long stayId : stayIds) {
            if (!reservedStayIds.contains(stayId)) {
                filteredStayIds.add(stayId);
            }
        }
        return stayRepository.findByIdInAndGuestNumberGreaterThanEqual(filteredStayIds, guestNumber);
    }

}


