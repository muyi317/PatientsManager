package com.muyi.patientsmanager.serialport;

import com.fazecast.jSerialComm.*;

import java.util.HashMap;
import java.util.Map;


public class JSerialComm {
    private SerialPort mSerialPort;
    private Map<Integer, byte[]> slaves = new HashMap<>();

    public SerialPort getmSerialPort() {
        return mSerialPort;
    }

    public void setmSerialPort(SerialPort mSerialPort) {
        this.mSerialPort = mSerialPort;
    }

    public Map<Integer, byte[]> getSlaves() {
        return slaves;
    }

    public void setSlaves(Map<Integer, byte[]> slaves) {
        this.slaves = slaves;
    }

    public JSerialComm(String portName, Map<Integer, byte[]> slaves) {
        mSerialPort = null;
        init(portName);
        this.slaves = slaves;
    }

    public void init(String portName) {
        mSerialPort = SerialPort.getCommPort(portName);
        mSerialPort.openPort();
        mSerialPort.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
        mSerialPort.setComPortParameters(19200, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        mSerialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING | SerialPort.TIMEOUT_WRITE_BLOCKING, 1000, 1000);
    }

    public void getAvailablePorts() {
        SerialPort[] ports = SerialPort.getCommPorts();
        System.out.println(ports[1].getSystemPortName() + "\n");
        System.out.println("\nAvailable Ports:\n");
        int portToUse = -1;
        for (int i = 0; i < ports.length; ++i) {
            System.out.println(ports[i].getSystemPortName() + ": " + ports[i].getDescriptivePortName() + " - " + ports[i].getPortDescription());
            portToUse = i;
            break;
        }
        if (portToUse < 0) {
            System.out.println("No relevant serial usb found on this system!");
            return;
        }
    }

    public void close() {
        if (mSerialPort != null && mSerialPort.isOpen()) {
            mSerialPort.closePort();
        }
    }

    private int write(byte[] data) {
        if (mSerialPort == null || !mSerialPort.isOpen()) {
            return 0;
        }
        return mSerialPort.writeBytes(data, data.length);
    }

    private int read(byte[] data) {
        if (mSerialPort == null || !mSerialPort.isOpen()) {
            return 0;
        }
        return mSerialPort.readBytes(data, data.length);
    }

    public int reciveOneWeight(Integer slave) {
        byte[] order = slaves.get(slave);
        write(order);
        byte[] data = new byte[100];
        try {
            int i = 0;
            while (mSerialPort.bytesAvailable() < 6 && i++ < 100)
                Thread.sleep(20);
        } catch (Exception e) {
        }

        int len = read(data);
        if (data == null) {
            close();
            return -1;
        }
        int weight = data[5] * 16 ^ 6 + data[4] * 16 ^ 4 + data[3] * 16 ^ 2 + data[2];
        System.out.format("%d \n", weight); // 接收到的重量

        close();
        return weight;
    }

    public int[] reciveFourWeight() {
        return new int[]{reciveOneWeight(1), reciveOneWeight(2), reciveOneWeight(3), reciveOneWeight(4)};
    }
}
