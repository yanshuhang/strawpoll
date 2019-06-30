package com.ysh.strawpoll.entity;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private String message;
    private List<Object> list;
}
