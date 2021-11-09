package com.muyi.patientsmanager.config;

import com.muyi.patientsmanager.serialport.JSerialComm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeanConfig {

    @Bean
    public JSerialComm jSerialComm(){
        Map<Integer, byte[]> slave = new HashMap<>();
        slave.put(1, new byte[]{(byte) 0x01, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0xC4, (byte) 0x0B});
        slave.put(2, new byte[]{(byte) 0x02, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0xC4, (byte) 0x38});
        slave.put(3, new byte[]{(byte) 0x03, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0xC5, (byte) 0xE9});
        slave.put(4, new byte[]{(byte) 0x04, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0xC4, (byte) 0x5E});
        return new JSerialComm("COM10", slave);
    }
}
