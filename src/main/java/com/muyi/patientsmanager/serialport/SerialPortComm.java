package com.muyi.patientsmanager.serialport;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class SerialPortComm {

    private double[] weight = new double[4];
    private double[] deltaWeight = new double[]{0,0,0,0};
    private JSONObject config = new JSONObject();
    private final String configFile = "config.json";

    @Autowired
    private JSerialComm jSerialComm;

    public double[] getDeltaWeight() {
        return deltaWeight;
    }

    public void setDeltaWeight(double[] deltaWeight) {
        this.deltaWeight = deltaWeight;
    }

    public double[] getWeight() {
        return weight;
    }

    public void setWeight(double[] weight) {
        this.weight = weight;
    }


    public SerialPortComm(){
        readConfigFile();
        if (Objects.equals(config, new JSONObject())) {
            this.deltaWeight = new double[]{0,0,0,0};
        } else {
            this.deltaWeight[0] = config.getDoubleValue("weight1");
            this.deltaWeight[1] = config.getDoubleValue("weight2");
            this.deltaWeight[2] = config.getDoubleValue("weight3");
            this.deltaWeight[3] = config.getDoubleValue("weight4");
        }
    }

    //从串口获取重量信息
    public double[] refreshWeight(){

//        int[] weights = jSerialComm.reciveFourWeight();
//        double weight1 = weights[0] - deltaWeight[0];
//        double weight2 = weights[1] - deltaWeight[1];
//        double weight3 = weights[2] - deltaWeight[2];
//        double weight4 = weights[3] - deltaWeight[3];

        double weight1 = 25.5 - deltaWeight[0] - (int) (Math.random()*3);
        double weight2 = 26.3 - deltaWeight[1] - (int) (Math.random()*3);
        double weight3 = 28.6 - deltaWeight[2] - (int) (Math.random()*3);
        double weight4 = 27.2 - deltaWeight[3] - (int) (Math.random()*3);

        weight[0] = weight1;
        weight[1] = weight2;
        weight[2] = weight3;
        weight[3] = weight4;

        return weight;
    }

    public String setZero(double weight1,double weight2,double weight3,double weight4){
        deltaWeight[0] = weight1;
        deltaWeight[1] = weight2;
        deltaWeight[2] = weight3;
        deltaWeight[3] = weight4;

        config.put("weight1",weight1);
        config.put("weight2",weight2);
        config.put("weight3",weight3);
        config.put("weight4",weight4);

        writeConfigFile();

        return "success";
    }

    public String setOriginal(){
        deltaWeight[0] = 0;
        deltaWeight[1] = 0;
        deltaWeight[2] = 0;
        deltaWeight[3] = 0;

        config.put("weight1",0);
        config.put("weight2",0);
        config.put("weight3",0);
        config.put("weight4",0);

        writeConfigFile();

        return "success";
    }

    public void readConfigFile(){
        try{
            File file=new File(configFile);
            String content= FileUtils.readFileToString(file,"UTF-8");
            JSONObject jsonObject=JSONObject.parseObject(content);
            this.config=jsonObject;
            System.out.println(this.config);
        }catch (IOException e){
            System.out.println("ConfigFile.readConfigFile"+e);
            System.out.println(this.config);
        }
    }

    //重新写入配置文件
    public boolean writeConfigFile(){
        try {
            if(config!=null){
                String json=JSONObject.toJSONString(config);
                File file=new File(configFile);
                FileUtils.writeStringToFile(file,json,"UTF-8",false);
                return true;
            }
        }catch (IOException e){
            System.out.println("ConfigFile.readConfigFile"+e);
        }
        return false;
    }
}
