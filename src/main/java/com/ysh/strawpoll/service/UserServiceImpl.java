package com.ysh.strawpoll.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysh.strawpoll.dao.PollDao;
import com.ysh.strawpoll.dao.UserDao;
import com.ysh.strawpoll.entity.Poll;
import com.ysh.strawpoll.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PollDao pollDao;

    public UserServiceImpl(UserDao userDao, PollDao pollDao) {
        this.userDao = userDao;
        this.pollDao = pollDao;
    }

    @Override
    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    @Override
    public PageInfo<Poll> getCreatedPoll(int userId, int pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<Poll> pollByUserId = pollDao.getPollByUserId(userId);
        PageInfo<Poll> pageInfo = new PageInfo<>(pollByUserId);
//        System.out.println(pageInfo);
        return pageInfo;
    }

    @Override
    public PageInfo<Poll> getVotedPoll(int userId, int pageNum) {
        PageHelper.startPage(pageNum, 8);
        List<Poll> polls = pollDao.getVotedPollByUserId(userId);
        PageInfo<Poll> pageInfo = new PageInfo<>(polls);
        return pageInfo;
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }
}
