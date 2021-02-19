package it.unicam.doit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.unicam.doit.dao.DoitUserDAO;

@SpringBootTest
public class DoitUserRepositoryTest {
	
	@Autowired 
	private DoitUserDAO userRepository;
	
	@Test
	void injectedComponentsAreNotNull(){
		assertNotNull(userRepository);
	}

	@Test
	public void it_can_find_the_user_after_save_it() {
		
	}
	
}
