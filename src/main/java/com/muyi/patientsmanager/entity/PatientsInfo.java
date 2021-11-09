package com.muyi.patientsmanager.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "info")
public class PatientsInfo {

    @Id
    private int _id;
    private List<Double> temp;
    private List<String> temp_time;
    private List<Double> weight;
    private List<String> weight_time;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public List<Double> getTemp() {
        return temp;
    }

    public void setTemp(List<Double> temp) {
        this.temp = temp;
    }

    public List<String> getTemp_time() {
        return temp_time;
    }

    public void setTemp_time(List<String> temp_time) {
        this.temp_time = temp_time;
    }

    public void addTemp_time(String temp_time) {
        this.temp_time.add(temp_time);
    }

    public List<Double> getWeight() {
        return weight;
    }

    public void setWeight(List<Double> weight) {
        this.weight = weight;
    }

    public List<String> getWeight_time() {
        return weight_time;
    }

    public void setWeight_time(List<String> weight_time) {
        this.weight_time = weight_time;
    }

    public void addWeight_time(String weight_time) {
        this.weight_time.add(weight_time);
    }
}
