package com.ysh.strawpoll.dao;

import com.ysh.strawpoll.entity.Poll;
import org.springframework.stereotype.Repository;

@Repository
public interface PollDao {
    Poll selectPoll(int id);

    int addPoll(Poll poll);

    int updatePeopleCount(int id);
}
