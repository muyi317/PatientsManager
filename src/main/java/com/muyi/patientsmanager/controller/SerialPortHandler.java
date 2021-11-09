package com.muyi.patientsmanager.controller;

import com.muyi.patientsmanager.serialport.SerialPortComm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/serialPort")
public class SerialPortHandler {

    @Autowired
    private SerialPortComm serialPortComm;

    @GetMapping("/getWeightValue")
    public double[] getWeightValue(){
        return serialPortComm.refreshWeight();
    }

    @GetMapping("/setOriginal")
    public String setOriginal(){
        return serialPortComm.setOriginal();
    }

    @GetMapping("/setZero/{weight1}/{weight2}/{weight3}/{weight4}")
    public String setZero(@PathVariable("weight1") double weight1,
                            @PathVariable("weight2") double weight2,
                            @PathVariable("weight3") double weight3,
                            @PathVariable("weight4") double weight4){
        return serialPortComm.setZero(weight1,weight2,weight3,weight4);
    }
}
