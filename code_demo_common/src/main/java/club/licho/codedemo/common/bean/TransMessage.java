package club.licho.codedemo.common.bean;

/**
 * ClassName:TransMessage
 *TCP客户端与服务器端通信的方式。
 * @author licho
 * @create 2018-04-16 0:17
 */
public class TransMessage {
    private String msgId;
    private String msgType;
    private Object msg;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
