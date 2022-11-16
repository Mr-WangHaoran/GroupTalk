package com.hr.controller;

import com.hr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin(originPatterns = "http://localhost:8082")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/inspectName/{username}")
    public String inspectRepeatName(@PathVariable("username") String name){
        return userService.inspectRepeatName(name);
    }

    @RequestMapping("/getUserList")
    public String getAllUserList(){
        return userService.getAllUserList();
    }
}
