package com.ysh.strawpoll.dao;

import com.ysh.strawpoll.entity.Poll;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollDao {
    Poll selectPoll(int id);

    int addPoll(Poll poll);

    int updatePeopleCount(int id);

    List<Poll> getPollByUserId(int userId);

    List<Poll> getVotedPollByUserId(int userId);
}
