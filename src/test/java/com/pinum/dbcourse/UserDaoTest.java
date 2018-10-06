package com.pinum.dbcourse;

import com.pinum.dbcourse.dao.postgres.SignalPostgresDao;
import com.pinum.dbcourse.dao.postgres.UserPostgresDao;
import com.pinum.dbcourse.entity.Signal;
import com.pinum.dbcourse.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserPostgresDao userPostgresDao;

    @Autowired
    private SignalPostgresDao signalPostgresDao;

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() {
//        JdbcTestUtils.deleteFromTables(userPostgresDao.getJdbc(), "users");
        User user = userService.generateUser();
        UUID save = userPostgresDao.save(user);
        System.out.println("Saved user: " + user);
    }

    @Test
    public void testBatchInsert() {
        List<User> users = IntStream.range(0, 100).mapToObj(value -> userService.generateUser()).collect(Collectors.toList());
        userPostgresDao.batchSave(users);
    }

    @Test
    public void testSaveSignal() {
        userService.generateSignals(Duration.ofMinutes(30), 100);
    }

    @Test
    public void testFindAllSignals() {
        List<Signal> all = signalPostgresDao.findAll();
        all.forEach(System.out::println);
    }
}
