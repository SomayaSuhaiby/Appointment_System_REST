package com.example.appointmentsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.appointmentsystem.model.User;
import com.example.appointmentsystem.repositories.UserRepository;

@SpringBootTest
class AppointmentsystemApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	void foundByEmailNotFoundTEest() {
		User user=userRepository.findByEmail("somaya@gmil.com");
		assertEquals(user, user);
		
	}

}
