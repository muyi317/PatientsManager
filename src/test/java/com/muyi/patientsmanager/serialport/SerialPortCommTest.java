package com.muyi.patientsmanager.serialport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SerialPortCommTest {

    @Autowired
    SerialPortComm serialPortComm;

    @Test
    public void initTest(){
        serialPortComm.readConfigFile();
    }

    @Test
    public void writeFileTest(){
        serialPortComm.writeConfigFile();
    }

}