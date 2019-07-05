package com.ysh.strawpoll.dao;

import com.ysh.strawpoll.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    int addUser(User user);

    User selectByUsername(String username);
}
