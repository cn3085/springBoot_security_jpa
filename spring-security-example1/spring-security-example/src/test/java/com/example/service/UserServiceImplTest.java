package com.example.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.SpringSecurityExampleApplication;
import com.example.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=SpringSecurityExampleApplication.class)
//servlet은 mock 객체 만들어서 쓰겠다
@WebAppConfiguration
public class UserServiceImplTest {

	@Autowired
	private UserService service;
	
	@Test
	public void testGetUserByUsername() {
		String username = "admin";
		User user = service.getUserByUsername(username);
		System.out.println(user);
		assertThat(user.getPassword(),is("admin"));
		
	}

}
