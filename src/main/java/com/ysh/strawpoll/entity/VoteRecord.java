package com.ysh.strawpoll.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoteRecord {
    private int id;
    private Integer userId;
    private String userIp;
    private Integer optionId;
    private int pollId;
    private LocalDateTime voteTime;
}
