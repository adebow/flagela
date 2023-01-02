package com.game.flagela;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.game.flagela.Repository.UserRepository;
import com.game.flagela.model.User;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("mayo@gmail.com");
		user.setPassword("lemon");
		user.setFirstname("ba");
		user.setLastname("ma");
		user.setUsername("bama");
		
		User savedUser = repo.save(user);
		
		User existUser = entityManager.find(User.class, savedUser.getId());
		
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());

}
	
	@Test
	public void testFinduserByUsername() {
		String username = "scrobo";
		
		User user = repo.findByUsername(username);
		
		assertThat(user).isNotNull();
	}
}
