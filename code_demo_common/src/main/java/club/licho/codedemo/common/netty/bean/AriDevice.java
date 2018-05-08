package club.licho.codedemo.common.netty.bean;

import com.alibaba.fastjson.JSON;

/**
 * ClassName:代表AiraDevice的消息体
 *
 * @author licho
 * @create 2018-04-30 12:18
 */
public class AriDevice {
    private String co2;//co2浓度
    private String pm25;//pm浓度
    private String maxTemperature;//最大温度
    private String minTemperature;//最小温度
    private String weather;//天气
    private String co;//co浓度

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public static AriDevice getDefaultVal(){
        AriDevice deviceDataBean = new AriDevice();
        deviceDataBean.setCo2("76");
        deviceDataBean.setCo("0.000012%");
        deviceDataBean.setMaxTemperature("23");
        deviceDataBean.setMinTemperature("10");
        deviceDataBean.setPm25("125ug/m^3");
        deviceDataBean.setWeather("多云");
        return deviceDataBean;
    }
}
