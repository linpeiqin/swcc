package cn.zf.swc.swcc.largescreen.config;

import cn.zf.swc.swcc.config.websocket.MyEndpointConfigure;
import cn.zf.swc.swcc.largescreen.util.LargeScreenUtil;
import cn.zf.swc.swcc.largescreen.vo.LargeScreenVo;
import cn.zf.swc.swcc.util.ErrorUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket获取实时数据推送大屏显示
 */
@Slf4j
@Component
@ServerEndpoint(value = "/websocket/largeScreen/{wcInfoId}", configurator = MyEndpointConfigure.class)
public class LargeScreenWSService {
    /**
     * 连接集合
     */
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();
    /**
     * 连接建立成功调用的方法
     */
    @Autowired
    private LargeScreenUtil largeScreenUtil;
    @OnOpen
    public void onOpen(Session session ,@PathParam("wcInfoId")Long  wcInfoId) {
        sessionMap.put(session.getId(), session);
        new Thread(()->{
            log.info("LargeScreenWSService 任务开始");
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            if (sessionMap.get(session.getId()) != null) {
                try {
                    send(session, mapper.writeValueAsString(largeScreenUtil.getLargeScreenInfo(wcInfoId)));
                    Thread.sleep(5000);
                } catch (Exception e) {
                    log.error(ErrorUtil.errorInfoToString(e));
                }
            }
            while (sessionMap.get(session.getId()) != null) {
                try {
                    Boolean isSensorDataUpdate = this.largeScreenUtil.isSensorDataUpdate(wcInfoId);
                    Boolean isSetDataUpdate = this.largeScreenUtil.isSetDataUpdate(wcInfoId);
                    LargeScreenVo largeScreenVo = null;
                    if (isSensorDataUpdate){
                        largeScreenVo = this.largeScreenUtil.setSensorData(wcInfoId,largeScreenVo);
                    }
                    if (isSetDataUpdate){
                        largeScreenVo = this.largeScreenUtil.setSetData(wcInfoId,largeScreenVo);
                    }
                    if (largeScreenVo!=null){
                        send(session,  mapper.writeValueAsString(largeScreenVo));
                    }
                    Thread.sleep(2000);
                } catch (Exception e) {
                    log.error(ErrorUtil.errorInfoToString(e));
                }
            }
            log.info("LargeScreenWSService 任务结束");
        }).start();
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        //从集合中删除
        sessionMap.remove(session.getId());
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        //输出到日志文件中
        log.error(ErrorUtil.errorInfoToString(error));
    }

    /**
     * 服务器接收到客户端消息时调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {

    }

    /**
     * 封装一个send方法，发送消息到前端
     */
    private void send(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error(ErrorUtil.errorInfoToString(e));
        }
    }

}
