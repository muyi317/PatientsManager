package com.muyi.patientsmanager.controller;

import com.muyi.patientsmanager.entity.Bed;
import com.muyi.patientsmanager.repository.BedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bed")
public class BedHandler {

    @Autowired
    private BedRepository bedRepository;

    @GetMapping("/findAll/{page}/{size}")
    public Page<Bed> findAll(@PathVariable("page") int page, @PathVariable("size")int size){
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("Id").descending());
        return bedRepository.findAll(pageable);
    }

    @PostMapping("/saveBed")
    public String save(@RequestBody Bed bed) {
        Pageable pageable = PageRequest.of(0,1);
        Page<Bed> page = bedRepository.findBedByRoomAndBed(bed.getRoom(), bed.getBed(),pageable);
        if (!page.getContent().isEmpty()) {
            bed.setId(page.getContent().get(0).getId());
        }
        try{
            bedRepository.save(bed);
            return "success";
        } catch (Throwable e) {
            return "error";
        }
    }

    @GetMapping("/findByRoomAndBed/{room}/{bed}/{page}/{size}")
    public Page<Bed> findByRoomAndBed(@PathVariable("room") String room,
                                          @PathVariable("bed")String bed,
                                          @PathVariable("page") int page,
                                          @PathVariable("size")int size)
    {
        if(room.equals("*")){
            room = "%";
        }else{
            room = "%"+room+"%";
        }
        if(bed.equals("*")){
            bed = "%";
        }else{
            bed = "%"+bed+"%";
        };
        Pageable pageable = PageRequest.of(page-1,size,Sort.by("Id").descending());
        return bedRepository.findBedByRoomAndBed(room, bed, pageable);
    }

    @GetMapping("/deleteById/{id}")
    public void findAll(@PathVariable("id") int id){
        bedRepository.deleteById(id);
    }
}
