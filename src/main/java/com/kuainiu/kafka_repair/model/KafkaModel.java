package com.kuainiu.kafka_repair.model;

/**
 * Created by tjh.
 * Date: 2019/10/10 下午6:37
 **/
public class KafkaModel {
    private String columnName;
    private  String columnValue;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }
}
