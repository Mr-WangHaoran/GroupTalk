package com.hr.pojos;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Group {

    @JSONField(serialize = false)
    private int id = 1;
    @JSONField(serialize = false)
    private Map<String, WebSocketSession> map = new ConcurrentHashMap<>();
    @JSONField(serialize = false)
    private Map<String, User> userMap = new ConcurrentHashMap<>();
    private List<User> list = new CopyOnWriteArrayList<>();
    private volatile int online_num;
}
