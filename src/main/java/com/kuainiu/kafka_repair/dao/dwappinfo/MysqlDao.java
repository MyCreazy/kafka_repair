package com.kuainiu.kafka_repair.dao.dwappinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by tjh.
 * Date: 2019/5/7 下午3:37
 **/
@Component
public class MysqlDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int excuteSql(String sql) {
        return jdbcTemplate.update(sql);
    }

    public Map query(String sql) {
        try {
            return jdbcTemplate.queryForMap(sql);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
