package com.ysh.strawpoll.service;

import com.ysh.strawpoll.dao.OptionDao;
import com.ysh.strawpoll.dao.PollDao;
import com.ysh.strawpoll.dao.VotedRecordDao;
import com.ysh.strawpoll.entity.Option;
import com.ysh.strawpoll.entity.Poll;
import com.ysh.strawpoll.entity.VoteRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PollServiceImpl implements PollService {
    private final PollDao pollDao;
    private final OptionDao optionDao;
    private final VotedRecordDao votedIpDao;

    public PollServiceImpl(PollDao pollDao, OptionDao optionDao, VotedRecordDao votedIpDao) {
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
    public void updateCount(List<VoteRecord> votedIpList) {
        int pollId = votedIpList.get(0).getPollId();
        ArrayList<Integer> optionIds = new ArrayList<>();
        for (VoteRecord votedIp : votedIpList) {
            optionIds.add(votedIp.getOptionId());
        }
        optionDao.updateCount(optionIds);
        pollDao.updatePeopleCount(pollId);
        votedIpDao.addVotedIPs(votedIpList);
    }

    @Override
    public List<VoteRecord> selectVotedIp(int pollId, int userId, String ip) {
        // 没有登录的用户使用ip查询
        if (userId == 0) {
            return votedIpDao.selectByIp(pollId, ip);
        }
        return votedIpDao.selectById(pollId, userId);
    }
}
