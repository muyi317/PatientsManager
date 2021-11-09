package com.muyi.patientsmanager.serialport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JSerialCommTest {

    @Autowired
    private JSerialComm jSerialComm;

    @Test
    void availablePort() {
        jSerialComm.getAvailablePorts();
    }

    @Test
    void testReadAndWrite() {
        jSerialComm.reciveOneWeight(4);
    }

}