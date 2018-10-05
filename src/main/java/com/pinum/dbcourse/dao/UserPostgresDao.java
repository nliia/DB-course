package com.pinum.dbcourse.dao;

import com.pinum.dbcourse.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserPostgresDao implements Dao<User, Long> {

    public static final String USER_INSERT = "INSERT INTO users (name, surname, address) VALUES (?, ?, ?)";
    public static final String USER_UPDATE = "UPDATE users SET name = ?, surname = ?, address = ? where id = ?";
    public static final String USER_SELECT = "SELECT id, name, surname, address FROM users where id = ?";
    public static final String USER_SELECT_ALL = "SELECT id, name, surname, address FROM users";
    public static final String USER_DELETE = "DELETE FROM users WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public <S extends User> Long save(S user) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(USER_INSERT, new String[]{"id"});
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getAddress());
            return ps;
        }, keyHolder);
        Long generatedKey = (Long) keyHolder.getKeys().get("id");
        user.setId(generatedKey);
        return generatedKey;
    }

    @Override
    public <S extends User> int[] batchSave(List<S> users) {
        return this.jdbc.batchUpdate(USER_INSERT, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, users.get(i).getName());
                ps.setString(2, users.get(i).getSurname());
                ps.setString(3, users.get(i).getAddress());
            }

            @Override
            public int getBatchSize() {
                return users.size();
            }
        });
    }

    @Override
    public <S extends User> int update(S user) {
        return jdbc.update(USER_UPDATE, user.getName(), user.getSurname(), user.getAddress(), user.getId());
    }

    @Override
    public User find(Long id) {
        return this.jdbc.queryForObject(USER_SELECT, new Object[]{id}, new UserMapper());
    }

    @Override
    public List<User> findAll() {
        return this.jdbc.query(USER_SELECT_ALL, new UserMapper());
    }

    @Override
    public void delete(Long id) {
        this.jdbc.update(USER_DELETE, id);
    }

    public static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId((Long) rs.getObject("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setAddress(rs.getString("address"));
            return user;
        }
    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }
}


