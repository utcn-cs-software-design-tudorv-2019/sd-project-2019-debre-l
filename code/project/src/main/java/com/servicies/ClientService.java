package com.servicies;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.entities.Client;
import com.repositories.ClientRepository;

@Service
public class ClientService {
	@Inject
	private ClientRepository clientRepository;
	
	public Client findByUsername(String username)
	{
		List<Client> rezultat = clientRepository.findAll();
		System.out.println("Repository");
		return rezultat.get(0);
	}
	
	public Client addClient(Client client) throws Exception
	{
		if (client==null)
		{
			throw new Exception(Client.class.getSimpleName());
		}
		else
		{
			return clientRepository.save(client);
		}
	}
}
