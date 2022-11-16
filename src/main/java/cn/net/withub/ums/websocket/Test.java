package cn.net.withub.ums.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cuizhibin on 2018/10/16.
 */
@ServerEndpoint(value="/ws/test")
public class Test {
    private Logger logger = LoggerFactory.getLogger(Test.class);

    public static ConcurrentHashMap<String, Session> linkMap = new ConcurrentHashMap<>();

    //连接时执行
    @OnOpen
    public void onOpen(Session session) {
        linkMap.put(session.getId(), session);
        logger.warn("新连接：{}");
    }

    //关闭时执行
    @OnClose
    public void onClose(Session session){
        linkMap.remove(session.getId());
        logger.warn("连接：{} 关闭");
    }

    //收到消息时执行
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.warn("收到用户{}的消息{}",message);
        session.getBasicRemote().sendText("收到的消息 "); //回复用户
    }

    //连接错误时执行
    @OnError
    public void onError(Session session, Throwable error){
        logger.warn("用户id为：{}的连接发送错误");
        error.printStackTrace();
    }

}