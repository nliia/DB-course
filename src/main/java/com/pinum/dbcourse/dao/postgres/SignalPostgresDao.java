package com.pinum.dbcourse.dao.postgres;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.pinum.dbcourse.dao.Dao;
import com.pinum.dbcourse.entity.Signal;
import com.pinum.dbcourse.entity.jsonmodels.SignalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SignalPostgresDao implements Dao<Signal, String> {

    private static final String SIGNAL_INSERT = "insert into signal(id, signal) values (?, ?::jsonb)";
    private static final String SIGNAL_FIND = "select id, signal from signal where id = ?";
    private static final String SIGNAL_FIND_ALL = "select id, signal from signal";

    @Autowired
    private JdbcTemplate jdbc;
    
    @Override
    public <S extends Signal> String save(S signal) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new StdDateFormat());
        String json = null;
        try {
            json = objectMapper.writeValueAsString(signal.getSignalInfo());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.jdbc.update(SIGNAL_INSERT, signal.getId(), json);
        return signal.getId();
    }

    @Override
    public <S extends Signal> int[] batchSave(List<S> entities) {
        return new int[0];
    }

    @Override
    public <S extends Signal> int update(S signal) {
        return 0;
    }

    @Override
    public Signal find(String uuid) {
        return this.jdbc.queryForObject(SIGNAL_FIND, new Object[]{uuid}, new SignalMapper());
    }

    @Override
    public List<Signal> findAll() {
        return this.jdbc.query(SIGNAL_FIND_ALL, new SignalMapper());
    }

    @Override
    public void delete(String uuid) {

    }

    public static final class SignalMapper implements RowMapper<Signal> {

        @Override
        public Signal mapRow(ResultSet rs, int rowNum) throws SQLException {
            ObjectMapper objectMapper = new ObjectMapper();
            Signal signal = new Signal();
            SignalInfo signalInfo = null;
            String signalInfoJson = rs.getString("signal");
            try {
                signalInfo = objectMapper.readValue(signalInfoJson, SignalInfo.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            signal.setId(rs.getString("id"));
            signal.setSignalInfo(signalInfo);
            return signal;
        }
    }
}
