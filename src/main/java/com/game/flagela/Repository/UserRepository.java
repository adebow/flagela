package com.game.flagela.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.flagela.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
    	
	}


