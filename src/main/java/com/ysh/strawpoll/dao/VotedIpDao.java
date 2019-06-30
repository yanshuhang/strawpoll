package com.ysh.strawpoll.dao;

import com.ysh.strawpoll.entity.VotedIp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotedIpDao {
    void addVotedIp(VotedIp votedIp);

    void addVotedIPs(List<VotedIp> list);

    List<VotedIp> select(@Param("pollId") int pollId, @Param("ip") String ip);
}
