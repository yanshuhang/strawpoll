package com.ysh.strawpoll.service;

import com.ysh.strawpoll.dao.OptionDao;
import com.ysh.strawpoll.dao.PollDao;
import com.ysh.strawpoll.dao.VotedIpDao;
import com.ysh.strawpoll.entity.Option;
import com.ysh.strawpoll.entity.Poll;
import com.ysh.strawpoll.entity.VotedIp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PollServiceImpl implements PollService {
    private final PollDao pollDao;
    private final OptionDao optionDao;
    private final VotedIpDao votedIpDao;

    public PollServiceImpl(PollDao pollDao, OptionDao optionDao, VotedIpDao votedIpDao) {
        this.pollDao = pollDao;
        this.optionDao = optionDao;
        this.votedIpDao = votedIpDao;
    }

    @Transactional
    @Override
    public int addPoll(Poll poll) {
        pollDao.addPoll(poll);
        int pollId = poll.getId();
        for (Option option : poll.getOptionList()) {
            option.setPollId(pollId);
        }
        optionDao.addOption(poll.getOptionList());
        return pollId;
    }

    @Override
    public Poll selectPoll(int id) {
        return pollDao.selectPoll(id);
    }

    @Override
    public void updateCount(List<VotedIp> votedIpList) {
        int pollId = votedIpList.get(0).getPollId();
        ArrayList<Integer> optionIds = new ArrayList<>();
        for (VotedIp votedIp : votedIpList) {
            optionIds.add(votedIp.getOptionId());
        }
        optionDao.updateCount(optionIds);
        pollDao.updatePeopleCount(pollId);
        votedIpDao.addVotedIPs(votedIpList);
    }

    @Override
    public List<VotedIp> selectVotedIp(int pollId, String ip) {
        return votedIpDao.select(pollId, ip);
    }
}
