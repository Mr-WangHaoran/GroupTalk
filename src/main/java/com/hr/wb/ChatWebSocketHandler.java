package com.hr.wb;

import com.alibaba.fastjson.JSON;
import com.hr.pojos.Group;
import com.hr.pojos.User;
import com.hr.utils.Names;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
    此类在一个连接建立后是单例的
 */
@Slf4j
public class ChatWebSocketHandler extends TextWebSocketHandler implements WebSocketHandler {

    //如果为private的话在生成动态代理的话@Autowired注入的依赖将为空, 这里怎么都注入不进去，所以我换了一种方法，通过参数传递
    private Group group;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        System.out.println(this);每次建立连接都指向同一个实例
        //相当于onOpen方法
        log.info("建立了一个连接"+session);
        String username = (String) session.getAttributes().get("username");
        User user = new User(Names.generator(),username,Names.formatDate(new Date()));
//        System.out.println(user);
        Group group = (Group) session.getAttributes().get("group");
        this.group = group;
        this.group.getList().add(user);
        this.group.getMap().put(username,session);
        this.group.getUserMap().put(username,user);
        this.group.setOnline_num(this.group.getOnline_num()+1);
        log.info("Create--userListSize="+this.group.getList().size());
        sendToAll(new TextMessage(JSON.toJSONString(this.group)));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        //相当于调用websocket.send()方法
        String username = (String) session.getAttributes().get("username");
        log.info("接收到了一条来自"+username+"消息："+message.getPayload());
        HashMap map = new HashMap();
        map.put("username",username);
        map.put("msg",message.getPayload());
        sendToAll(new TextMessage(JSON.toJSONString(map)));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error(exception.toString());
        //相当于发生了连接错误
        remove(session);
        sendToAll(new TextMessage(JSON.toJSONString(this.group)));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//        相当于关闭连接
        log.info("关闭了一个连接");
        remove(session);
        sendToAll(new TextMessage(JSON.toJSONString(this.group)));
    }

    public void remove(WebSocketSession session){
        String username = (String) session.getAttributes().get("username");
        Names.removeName(username);
        group.getMap().remove(username);
        group.getList().remove(group.getUserMap().get(username));
        log.info("Close--userListSize="+group.getList().size());
        group.setOnline_num(group.getOnline_num()-1);
    }

    public synchronized void sendToAll(WebSocketMessage<?> message){
        Map<String, WebSocketSession> map = group.getMap();
        map.values().stream().forEach(session->{
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
