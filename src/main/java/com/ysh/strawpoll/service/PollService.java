package com.ysh.strawpoll.service;

import com.ysh.strawpoll.entity.Poll;
import com.ysh.strawpoll.entity.VoteRecord;

import java.util.List;

public interface PollService {
    int addPoll(Poll poll);

    Poll selectPoll(int id);

    void updateCount(List<VoteRecord> votedIpList);

    List<VoteRecord> selectVotedIp(int pollId, int userId, String ip);
}
