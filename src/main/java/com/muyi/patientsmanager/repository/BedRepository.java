package com.muyi.patientsmanager.repository;

import com.muyi.patientsmanager.entity.Bed;
import com.muyi.patientsmanager.entity.Patients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BedRepository extends JpaRepository<Bed, Integer> {
    @Query(value = "select * from bed where Room like ? and Bed like ?", nativeQuery = true)
    public Page<Bed> findBedByRoomAndBed(String Room, String Bed, Pageable pageable);
}
