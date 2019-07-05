package com.ysh.strawpoll.service;

import com.github.pagehelper.PageInfo;
import com.ysh.strawpoll.entity.Poll;
import com.ysh.strawpoll.entity.User;

public interface UserService {
    int addUser(User user);

    User selectByUsername(String username);

    PageInfo<Poll> getCreatedPoll(int userId, int pageNum);

    PageInfo<Poll> getVotedPoll(int userId, int pageNum);
}
