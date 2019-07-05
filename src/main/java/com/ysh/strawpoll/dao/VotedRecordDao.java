package com.ysh.strawpoll.dao;

import com.ysh.strawpoll.entity.VoteRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotedRecordDao {
    void addVotedIp(VoteRecord votedIp);

    void addVotedIPs(List<VoteRecord> list);

    List<VoteRecord> selectByIp(@Param("pollId") int pollId, @Param("ip") String ip);

    List<VoteRecord> selectById(@Param("pollId") int pollId, @Param("id") int userId);
}
