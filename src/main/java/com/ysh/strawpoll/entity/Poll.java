package com.ysh.strawpoll.entity;

import lombok.Data;

import java.util.List;

@Data
public class Poll {
    private int id;
    private String title;
    private byte limit;
    private int count;
    private int userId;
    private List<Option> optionList;
}
