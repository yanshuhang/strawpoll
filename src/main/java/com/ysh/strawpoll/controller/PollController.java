package com.ysh.strawpoll.controller;

import com.ysh.strawpoll.entity.Poll;
import com.ysh.strawpoll.entity.Result;
import com.ysh.strawpoll.entity.VotedIp;
import com.ysh.strawpoll.service.PollService;
import com.ysh.strawpoll.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("poll")
public class PollController {
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping("/create")
    public int createPoll(@RequestBody Poll poll) {
        return pollService.addPoll(poll);
    }

    @GetMapping("/get/{id}")
    public Result getPoll(@PathVariable int id, HttpServletRequest request) {
        String message;
        Poll poll = pollService.selectPoll(id);
        if (poll != null) {
            message = "success";
        } else {
            message = "fail";
        }
        String ip = IpUtil.getIp(request);
        List<VotedIp> votedIps = pollService.selectVotedIp(id, ip);
        Result result = new Result();
        ArrayList<Object> list = new ArrayList<>();
        list.add(poll);
        list.add(votedIps);
        result.setMessage(message);
        result.setList(list);
        return result;
    }

    @PostMapping("/voting")
    public Result voting(@RequestBody List<VotedIp> votedIps, HttpServletRequest request) {
        int pollId = votedIps.get(0).getPollId();
        String ip = IpUtil.getIp(request);
        List<VotedIp> votedIpList = pollService.selectVotedIp(pollId, ip);
        // 如果该ip没投过票，如果已投过票直接返回
        if (votedIpList.size() == 0) {
            for (VotedIp votedIp : votedIps) {
                votedIp.setUserIp(ip);
            }
            pollService.updateCount(votedIps);
        }
        return getPoll(votedIps.get(0).getPollId(),request);
    }

    @GetMapping("/ip")
    public String getIp(HttpServletRequest request) {
        return IpUtil.getIp(request);
    }
}
