package com.muyi.patientsmanager.repository;

import com.muyi.patientsmanager.entity.Patients;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

public interface PatientsRepository extends JpaRepository<Patients, Integer> {

    @Query(value = "select * from Patients where Patient_id like ? and Patient_name like ?", nativeQuery = true)
    public Page<Patients> findPatientsByIdAndName(String Patient_id, String Patient_name, Pageable pageable);
}
