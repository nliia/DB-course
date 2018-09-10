package com.pinum.dbcourse;

import com.pinum.dbcourse.entity.User;
import com.pinum.dbcourse.entity.jsonmodels.UserInfo;
import com.pinum.dbcourse.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbCourseApplication.class, args);
		UserRepository userRepository = ApplicationContextProvider.getApplicationContext().getBean("userRepository", UserRepository.class);
		User user = new User();
		UserInfo userInfo = new UserInfo();
		userInfo.setName("asdasd");
		userInfo.setSurname("asdasd");
		user.setUserInfo(userInfo);
		userRepository.save(user);
	}
}
