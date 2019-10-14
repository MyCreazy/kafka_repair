package com.kuainiu.kafka_repair.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kuainiu.kafka_repair.dao.dwappinfo.MysqlDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tjh.
 * Date: 2019/5/7 下午3:19
 **/
@Service
public class DataFilterService {
    private static final Logger logger = LoggerFactory.getLogger(DataFilterService.class);

    @Autowired
    private MysqlDao mysqlDao;

    public void solveMessage(String jsonStr) {
        logger.info("收到数据[" + jsonStr + "]");
        if (!StringUtils.isEmpty(jsonStr)) {
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            //只要插入操作的数据
            String opt= jsonObject.get("eventType").toString();
            if(opt.toUpperCase().equals("INSERT")) {
                JSONArray jsonKeyArray = JSONObject.parseArray(jsonObject.get("keys").toString());
                String individual_job_id = jsonKeyArray.getJSONObject(0).getString("columnValue");
                String individual_job_individual_id = "";
                List<HashMap> list = JSONObject.parseArray(jsonObject.get("columns").toString(), HashMap.class);
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).get("columnName").equals("individual_job_individual_id")) {
                        individual_job_individual_id = list.get(i).get("columnValue").toString();
                        break;
                    }
                }

                ////直接拿到individual_job_individual_id去查询individual_extend_new查询
                String sql = String.format("select individual_extend_individual_id from individual_extend_new where individual_extend_id = '%s'", individual_job_individual_id);
                logger.info("查询sql[" + sql + "]");
                Map<String, Object> result = mysqlDao.query(sql);
                if (result.size() > 0) {
                    String individual_extend_individual_id = result.get("individual_extend_individual_id").toString();
                    sql = String.format("update individual_job set individual_job_individual_id='%s' where individual_job_id='%s'", individual_extend_individual_id, individual_job_id);
                    mysqlDao.excuteSql(sql);
                    logger.info("更新sql[" + sql + "]");
                }
            }
        }
    }
}
