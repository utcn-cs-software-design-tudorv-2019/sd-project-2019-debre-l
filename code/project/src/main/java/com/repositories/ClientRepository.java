package com.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository <Client, Integer> {
	//Client findByUsername(String username);
	List<Client> findAll();
	Client save(Client user);
}
