package com.agosh.account.test.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agosh.account.test.entity.UserEntity;
import com.agosh.account.test.exception.RecordNotFoundException;
import com.agosh.account.test.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserEntity createOrUpdateUser(UserEntity userEntity) throws RecordNotFoundException {
		if (userEntity.getId() == null) {
			userEntity = userRepository.save(userEntity);

			return userEntity;
		} else {
			Optional<UserEntity> userOptional = userRepository.findById(userEntity.getId());

			if (userOptional.isPresent()) {
				UserEntity newUser = userOptional.get();
				newUser.setFirstName(userEntity.getFirstName());
				newUser.setLastName(userEntity.getLastName());
				newUser.setDateOfBirth(userEntity.getDateOfBirth());
				newUser = userRepository.save(newUser);

				return newUser;
			} else {
				throw new RecordNotFoundException("No user record exist for given id");
			}
		}
	}

	public UserEntity getUserById(Long id) throws RecordNotFoundException {
		Optional<UserEntity> userOptional = userRepository.findById(id);

		if (userOptional.isPresent()) {
			return userOptional.get();
		} else {
			throw new RecordNotFoundException("No user record exist for given id");
		}
	}

	public void deleteUserById(Long id) throws RecordNotFoundException {
		Optional<UserEntity> userOptional = userRepository.findById(id);

		if (userOptional.isPresent()) {
			userRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No user record exist for given id");
		}
	}

	public boolean isDOBValid(UserEntity userEntity) {
		if (userEntity.getDateOfBirth() != null) {
			LocalDateTime currentDateTime = LocalDateTime.now();
			return userEntity.getDateOfBirth().isBefore(currentDateTime);
		}
		return false;
	}

}
