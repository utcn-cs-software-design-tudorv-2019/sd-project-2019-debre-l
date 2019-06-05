package com.sec.service;

import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sec.entity.Role;
import com.sec.entity.User;
import com.sec.repository.RoleRepository;
import com.sec.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private final String USER_ROLE = "ROLE_USER";
	private final String ADMIN_ROLE = "ADMIN_ROLE";

	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		System.out.println("loadUserByUsername"+user);
		return new UserDetailsImpl(user);
	}
	
	public User getById(Long id) throws Exception
	{
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent())
		{
			return user.get();
		}
		else
		{
			throw new Exception("User not found");
		}
	}
	
	public User getByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}

	public User getByEmail(String username) {
		return userRepository.findByUsername(username);
	}

	public String registerUser(User userToRegister) {
		User userCheck = userRepository.findByEmail(userToRegister.getEmail());

		if (userCheck != null)
			return "alreadyExists";

		Role userRole = roleRepository.findByRole(USER_ROLE);
		if (userRole != null) {
			userToRegister.getRoles().add(userRole);
		} else {
			userToRegister.addRoles(USER_ROLE);
		}
		
		userToRegister.setEnabled(false);
		userToRegister.setActivation(generateKey());
		userRepository.save(userToRegister);
		
		
		return userToRegister.getActivation();
	}
	
	public Iterable<User> getAllRegistredUser()
	{
		return userRepository.findAll();
	}

	public String generateKey()
    {
		String key = "";
		Random random = new Random();
		char[] word = new char[16]; 
		for (int j = 0; j < word.length; j++) {
			word[j] = (char) ('a' + random.nextInt(26));
		}
		String toReturn = new String(word);
		log.debug("random code: " + toReturn);
		return new String(word);
    }

	public String userActivation(String code) {
		User user = userRepository.findByActivation(code);
		if (user == null)
		    return "noresult";
		
		user.setEnabled(true);
		user.setActivation("");
		userRepository.save(user);
		return "ok";
	}
	
	public void updateUser(User updatedUser, User newData)
	{
		if(!newData.getName().isEmpty())
		{
			updatedUser.setName(newData.getName());
		}
		if(!newData.getEmail().isEmpty())
		{
			updatedUser.setEmail(newData.getEmail());
		}
		if(!newData.getCnp().isEmpty())
		{
			updatedUser.setCnp(newData.getCnp());
		}
		if(!newData.getAddress().isEmpty())
		{
			updatedUser.setAddress(newData.getAddress());
		}
		if(!newData.getNrTel().isEmpty())
		{
			updatedUser.setNrTel(newData.getNrTel());
		}
		if(!newData.getPassword().isEmpty())
		{
			updatedUser.setPassword(newData.getPassword());
		}
		userRepository.save(updatedUser);
	}
}
