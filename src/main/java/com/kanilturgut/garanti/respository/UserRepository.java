package com.kanilturgut.garanti.respository;

import com.kanilturgut.garanti.model.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    User findUserByUsername(String username);
}
