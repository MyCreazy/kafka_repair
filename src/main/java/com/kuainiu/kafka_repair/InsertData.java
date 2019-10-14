package com.kuainiu.kafka_repair;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by tjh.
 * Date: 2019/5/9 下午5:32
 **/
public class InsertData {


    public static void main(String[] args) {
        try {
            String updateStr = "";
            String conditonStr="";
            String[] updateArray = updateStr.split("\\,");

            String[] ssx = conditonStr.split("\\,");
            for (int i = 0; i < ssx.length; i++) {
                String tmpStr = String.format("update individual_job set individual_job_individual_id='%s' where individual_job_id='%s';", updateArray[i], ssx[i]);
                System.out.println(tmpStr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


