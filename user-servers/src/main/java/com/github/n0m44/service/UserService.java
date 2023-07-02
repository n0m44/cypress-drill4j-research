package com.github.n0m44.service;

import com.github.n0m44.UserMapper;
import com.github.n0m44.storage.entity.UserEntity;
import com.github.n0m44.storage.repository.UserRepository;
import org.hibernate.annotations.NotFound;
import org.openapitools.model.CredentialsDto;
import org.openapitools.model.UserDto;
import org.openapitools.model.UserWithPasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserEntity> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    /**
     * A description of the entire Java function.
     *
     * @param  username  description of parameter
     * @return           description of return value
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByLogin(username)
                .map(u -> new User(u.getLogin(), u.getPassword(), List.of()))
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username)));
    }

    public UserDto createNewUser(CredentialsDto credentialsDto) throws UserAlreadyExistsException {
        final UserEntity userEntity = UserMapper.toUserEntity(credentialsDto);
        userEntity.setPassword(passwordEncoder.encode(credentialsDto.getPassword()));
        try {
            final UserEntity createdUser = userRepository.save(userEntity);
            return UserMapper.toUserDto(createdUser);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException("User with login " + userEntity.getLogin() + " already exists");
        }
    }

    public List<UserDto> getListUsers() {
        // TODO limit and offset
        final LinkedList<UserDto> result = new LinkedList<>();
        userRepository.findAll().forEach(userEntity -> result.add(UserMapper.toUserDto(userEntity)));
        return result;
    }

    public UserDto getUser(Long id) throws UserNotFoundException {
        final UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found by id " + id));
        return UserMapper.toUserDto(userEntity);
    }

    public UserDto updateUser(UserWithPasswordDto userDto) throws UserNotFoundException {
        final UserEntity userEntity = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found by id " + userDto.getId()));
        userEntity.setLogin(userDto.getLogin());
        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);
        return UserMapper.toUserDto(userEntity);
    }

    public UserDto createUser(UserWithPasswordDto userDto) throws UserAlreadyExistsException {
        try {
            final UserEntity userEntity = UserMapper.toUserEntity(userDto);
            userEntity.setId(null);
            final UserEntity newUser = userRepository.save(userEntity);
            return UserMapper.toUserDto(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException("User with login " + userDto.getLogin() + " already exists");
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
