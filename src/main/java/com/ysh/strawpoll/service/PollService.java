package com.ysh.strawpoll.service;

import com.ysh.strawpoll.entity.Poll;
import com.ysh.strawpoll.entity.VotedIp;

import java.util.List;

public interface PollService {
    int addPoll(Poll poll);

    Poll selectPoll(int id);

    void updateCount(List<VotedIp> votedIpList);

    List<VotedIp> selectVotedIp(int pollId, String ip);
}
