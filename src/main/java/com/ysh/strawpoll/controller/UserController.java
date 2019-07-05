package com.ysh.strawpoll.controller;

import com.auth0.jwt.JWT;
import com.github.pagehelper.PageInfo;
import com.ysh.strawpoll.entity.Poll;
import com.ysh.strawpoll.entity.ResponseData;
import com.ysh.strawpoll.entity.User;
import com.ysh.strawpoll.service.UserService;
import com.ysh.strawpoll.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public String register(@Valid @RequestBody User user) {
        userService.addUser(user);
        return TokenUtil.createToken(user.getId(), user.getUsername(), user.getPassword());
    }

    @PostMapping("login")
    public String login(@RequestBody User user) {
        User userReturned = userService.selectByUsername(user.getUsername());
        String token = null;
        if (userReturned.getPassword().equals(user.getPassword())) {
            token = TokenUtil.createToken(userReturned.getId(), userReturned.getUsername(),
                    userReturned.getPassword());
            log.info("用户 " + userReturned.getUsername() + " 登录");
        }
        return token;
    }

    @PostMapping("autologin")
    public int aotuLogin(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (TokenUtil.verifyToken(token)) {
            return 1;
        }
        return 0;
    }

    @GetMapping("poll/{pageNum}")
    public ResponseData getPoll(HttpServletRequest request, @PathVariable int pageNum) {
        String token = request.getHeader("Authorization");
        if (!TokenUtil.verifyToken(token)) {
            return null;
        }
        int userId = JWT.decode(token).getClaim("userId").asInt();
        PageInfo<Poll> createdPoll = userService.getCreatedPoll(userId, pageNum);
        PageInfo<Poll> votedPoll = userService.getVotedPoll(userId, pageNum);
        ResponseData responseData = new ResponseData();
        responseData.setMessage("success");
        ArrayList<Object> list = new ArrayList<>();
        list.add(createdPoll);
        list.add(votedPoll);
        responseData.setList(list);
        return responseData;
    }

}
