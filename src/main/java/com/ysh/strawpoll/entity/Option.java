package com.ysh.strawpoll.entity;

import lombok.Data;

@Data
public class Option {
    private int id;
    private String title;
    private int count;
    private int pollId;
}
