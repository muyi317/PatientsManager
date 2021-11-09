package com.muyi.patientsmanager.controller;

import com.muyi.patientsmanager.entity.Patients;
import com.muyi.patientsmanager.repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/patients")
public class PatientsHandler {

    @Autowired
    private PatientsRepository patientsRepository;

    @GetMapping("/findAll/{page}/{size}")
    public Page<Patients> findAll(@PathVariable("page") int page, @PathVariable("size")int size){
        Pageable pageable = PageRequest.of(page-1,size,Sort.by("id").descending());
        return patientsRepository.findAll(pageable);
    }

    @PostMapping("/savePatient")
    public String save(@RequestBody Patients patient) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        patient.setVisit_time(df.format(new Date(System.currentTimeMillis())));
        Patients result = patientsRepository.save(patient);
        if(result != null) {
            return "success";
        }else{
            return "error";
        }
    }

    @GetMapping("/findByIdAndName/{id}/{name}/{page}/{size}")
    public Page<Patients> findByIdAndName(@PathVariable("id") String id,
                                          @PathVariable("name")String name,
                                          @PathVariable("page") int page,
                                          @PathVariable("size")int size)
    {
        if(id.equals("*")){
            id = "%";
        }else{
            id = "%"+id+"%";
        }
        if(name.equals("*")){
            name = "%";
        }else{
            name = "%"+name+"%";
        };
        Pageable pageable = PageRequest.of(page-1,size,Sort.by("Id").descending());
        return patientsRepository.findPatientsByIdAndName(id, name, pageable);
    }

    @GetMapping("/deleteById/{id}")
    public void findAll(@PathVariable("id") int id){
        patientsRepository.deleteById(id);
    }
}
