package com.muyi.patientsmanager.controller;

import com.muyi.patientsmanager.entity.PatientsInfo;
import com.muyi.patientsmanager.repository.PatientsInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patientsInfo")
public class PatientsInfoHandler {

    @Autowired
    private PatientsInfoRepository patientsInfoRepository;

    @GetMapping("/findAll")
    public List<PatientsInfo> findAll(){
        return patientsInfoRepository.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<PatientsInfo> findById(@PathVariable("id") Integer id){
        return patientsInfoRepository.findById(id);
    }

    @PutMapping("/updatePatientTemp")
    public String updatePatientTemp(@RequestBody PatientsInfo patientInfo) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        patientInfo.addTemp_time(df.format(new Date(System.currentTimeMillis())));
        try{
            patientsInfoRepository.save(patientInfo);
            return "success";
        } catch (Throwable e) {
            return "error";
        }
    }

    @PutMapping("/updatePatientWeight")
    public String updatePatientWeight(@RequestBody PatientsInfo patientInfo) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        patientInfo.addWeight_time(df.format(new Date(System.currentTimeMillis())));
        try{
            patientsInfoRepository.save(patientInfo);
            return "success";
        } catch (Throwable e) {
            return "error";
        }
    }

    @GetMapping("/deletePatientInfoById/{id}")
    public void deletePatientInfo(@PathVariable("id") Integer id) {
        System.out.println(id);
        patientsInfoRepository.deleteById(id);
    }
}

