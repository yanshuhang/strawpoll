package com.ysh.strawpoll.entity;

import lombok.Data;

@Data
public class VotedIp {
    private int id;
    private Integer userId;
    private String userIp;
    private Integer optionId;
    private int pollId;
}
