package com.myInstitute.ui.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myInstitute.domain.User;
import com.myInstitute.exception.NotFoundException;
import com.myInstitute.repository.UserRepository;
import com.myInstitute.ui.CreateUserRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageSource messageSource;

	@GetMapping
	public ResponseEntity<Collection<User>> getUsers() {
		List<User> findAll = userRepository.findAll();
		return new ResponseEntity<>(findAll, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) throws NotFoundException {
		return new ResponseEntity<>(findUser(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<User> addUser(@Valid @RequestBody CreateUserRequest request) {
		User user = new ModelMapper().map(request, User.class);
		user = userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<User> updateUser(@Valid @RequestBody CreateUserRequest request) throws NotFoundException {
		if (request.getUserId() == null) {
			throw new NotFoundException(messageSource.getMessage("user.notfound", new Long[] { request.getUserId() }, null));
		}
		User user = findUser(request.getUserId());
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK); 
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(name = "id", required = true) Long id)
			throws NotFoundException {
		findUser(id);
		userRepository.deleteById(id);
		return new ResponseEntity<>(messageSource.getMessage("user.deleted", new Long[] { id }, null),
				HttpStatus.ACCEPTED);
	}

	private User findUser(Long id) throws NotFoundException {
		Optional<User> findById = userRepository.findById(id);
		if (!findById.isPresent()) {
			throw new NotFoundException(messageSource.getMessage("user.notfound", new Long[] { id }, null));
		}
		return findById.get();
	}

}
