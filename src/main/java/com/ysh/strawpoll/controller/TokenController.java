package com.ysh.strawpoll.controller;

import com.ysh.strawpoll.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("token")
public class TokenController {

    @GetMapping("where")
    public String getToken() {
        TokenUtil.createToken(111, "yanshuhang", "hjkiol");
        return "success";
    }

    @PostMapping("header")
    public String postToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        log.info("token::" + token);
        if (token == null) {
            return "false";
        }
        return "success";
    }
}
