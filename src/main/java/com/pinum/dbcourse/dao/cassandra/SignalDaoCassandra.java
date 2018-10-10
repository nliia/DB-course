package com.pinum.dbcourse.dao.cassandra;

import com.datastax.driver.core.ResultSet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.pinum.dbcourse.cassandra.CassandraConnector;
import com.pinum.dbcourse.dao.Dao;
import com.pinum.dbcourse.entity.Signal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author lnurullina
 */
@Repository
public class SignalDaoCassandra implements Dao<Signal, UUID> {

    private static final String SIGNAL_INSERT = "insert into signal(id, signal) values (?, ?)";
    private static final String SIGNAL_FIND = "select id, signal from signal where id = ?";
    private static final String SIGNAL_FIND_ALL = "select id, signal from signal";

    @Autowired
    CassandraConnector cassandraConnector;

    @Override
    public <S extends Signal> UUID save(S signal) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new StdDateFormat());
        String json = null;
        try {
            json = objectMapper.writeValueAsString(signal.getSignalInfo());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        cassandraConnector.getSession().execute(SIGNAL_INSERT, signal.getId(), json);
        return signal.getId();
    }

    @Override
    public <S extends Signal> int update(S entity) {
        return 0;
    }

    @Override
    public Signal find(UUID uuid) {
//        ResultSet rs =  this.cassandraConnector.getSession().execute(SIGNAL_FIND, uuid);
//        Signal signal = new Signal();
//        rs.forEach(r ->{signal = new Signal(
//                r.getUUID("id"),
//                r.getString("title"),
//                r.getString("subject"))});
//
//
//        return signal;

    return null; }

    @Override
    public int[] batchSave(List entities) {
        return new int[0];
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void delete(UUID s) {

    }

}
