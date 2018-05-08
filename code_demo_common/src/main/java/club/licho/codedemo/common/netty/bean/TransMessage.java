package club.licho.codedemo.common.netty.bean;

/**
 * ClassName:用作服务器与客户端通信的TransMessage
 *
 * @author licho
 * @create 2018-04-30 12:16
 */
public class TransMessage {
    private String accessToken;//验证token
    private String deviceType;//设备类型
    private String deviceId;//设备id
    private String deviceName;//设备名称
    private String msgType;//消息类型
    private Object msgBody;//消息体

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Object getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(Object msgBody) {
        this.msgBody = msgBody;
    }

    @Override
    public String toString() {
        return "TransMessage{" +
                "accessToken='" + accessToken + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", msgType='" + msgType + '\'' +
                ", msgBody=" + msgBody +
                '}';
    }
}
