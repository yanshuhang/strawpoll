package com.ysh.strawpoll.dao;

import com.ysh.strawpoll.entity.Option;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionDao {
    int addOption(List<Option> optionList);
    int updateCount(List<Integer> ids);
}
