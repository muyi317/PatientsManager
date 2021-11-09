package com.muyi.patientsmanager.repository;

import com.muyi.patientsmanager.entity.PatientsInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientsInfoRepository extends MongoRepository<PatientsInfo, Integer> {
}
