package com.ysh.strawpoll.controller;

import com.auth0.jwt.JWT;
import com.ysh.strawpoll.entity.Poll;
import com.ysh.strawpoll.entity.ResponseData;
import com.ysh.strawpoll.entity.VoteRecord;
import com.ysh.strawpoll.service.PollService;
import com.ysh.strawpoll.util.IpUtil;
import com.ysh.strawpoll.util.TokenUtil;
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
    public ResponseData getPoll(@PathVariable int id, HttpServletRequest request) {
        Poll poll = pollService.selectPoll(id);
        // 不存在该投票
        if (poll == null) {
            String message = "fail";
            ResponseData fail = new ResponseData();
            fail.setMessage(message);
            return fail;
        } else {
            String message = "success";
            // 获得用户ip
            String ip = IpUtil.getIp(request);
            // 获得用户请求中的token
            String token = request.getHeader("Authorization");
            // 存放投票记录
            List<VoteRecord> voteRecord;
            if (!TokenUtil.verifyToken(token)) {
                // 根据ip查询
                voteRecord = pollService.selectVotedIp(id, 0, ip);
            } else {
                int userId = JWT.decode(token).getClaim("userId").asInt();
                // 根据id查询
                voteRecord = pollService.selectVotedIp(id, userId, ip);
            }
            ResponseData result = new ResponseData();
            ArrayList<Object> list = new ArrayList<>();
            list.add(poll);
            list.add(voteRecord);
            result.setMessage(message);
            result.setList(list);
            return result;
        }
    }

    @PostMapping("/voting")
    public ResponseData voting(@RequestBody List<VoteRecord> votedIps, HttpServletRequest request) {
        int pollId = votedIps.get(0).getPollId();
        int userId = votedIps.get(0).getUserId();
        String ip = IpUtil.getIp(request);
        List<VoteRecord> votedIpList = pollService.selectVotedIp(pollId, userId, ip);
        // 如果该ip没投过票，如果已投过票直接返回
        if (votedIpList.size() == 0) {
            for (VoteRecord votedIp : votedIps) {
                votedIp.setUserIp(ip);
            }
            pollService.updateCount(votedIps);
        }
        return getPoll(votedIps.get(0).getPollId(), request);
    }
}
