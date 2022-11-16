package com.hr.service;

import com.alibaba.fastjson.JSON;
import com.hr.pojos.Group;
import com.hr.utils.Names;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    Group group;

    public String inspectRepeatName(String name){
        Map<String,String> map = new HashMap<>();
        if(name.length()<2){
            return JSON.toJSONString(map.put("error","用户名为空，用户非法操作！"));
        }
        if(Names.exists(name)){
            map.put("error","用户名已经存在");
            return JSON.toJSONString(map);
        }
        map.put("permit","ok");
        //将用户名添加进集合中
        Names.addName(name);
        return JSON.toJSONString(map);
    }

    public String getAllUserList(){
        Map map = new HashMap();
        map.put("userList",group.getList());
        return JSON.toJSONString(map);
    }
}
