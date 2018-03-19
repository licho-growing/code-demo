package com.ingcore.common.utils;


import com.ingcore.common.utils.mjSms.SmsClientSend;

import net.sf.json.JSONObject;

import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SmsUtils {
	private static Logger logger = LoggerFactory.getLogger(SmsUtils.class);
	
	/**
	 * 发送短信：创蓝文化   蜗牛 剧角 冠宇
	 * @param mobile
	 * @param msg
	 * @return
	 */
	public static boolean sendSms(String mobile, String msg) {
		try {
			String url = "http://222.73.117.156/msg/HttpBatchSendSM";
			String account = "Thyc888";
			String pswd	   = "Thyc_888";
			boolean needstatus = true;
			Map<String, String> params = new HashMap<String, String>();
			params.put("account", 		account);
			params.put("pswd", 			pswd);
			params.put("mobile", 		mobile);
			params.put("needstatus", 	String.valueOf(needstatus));
			params.put("msg", 			msg);
			params.put("product", 		null);
			params.put("extno", 		null);
			String result = HttpUtil.doGet(url, params);
			if (result.contains(",0")) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 发送短信：创蓝文化  传奇的子账号
	 * @param mobile
	 * @param msg
	 * @return
	 */
	public static boolean sendSms_chuanqi(String mobile, String msg) {
		try {
			String url = "http://222.73.117.156/msg/HttpBatchSendSM";
			String account = "Thyc88_SAGA";
			String pswd	   = "Saga111111";
			boolean needstatus = true;
			Map<String, String> params = new HashMap<String, String>();
			params.put("account", 		account);
			params.put("pswd", 			pswd);
			params.put("mobile", 		mobile);
			params.put("needstatus", 	String.valueOf(needstatus));
			params.put("msg", 			msg);
			params.put("product", 		null);
			params.put("extno", 		null);
			String result = HttpUtil.doGet(url, params);
			if (result.contains(",0")) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 美嘉短信渠道发送短息
	 * @param mobile
	 * @param content
	 * @return
	 */
	public static boolean mjSendSms(String mobile, String content) {
		//String send = SmsClientSend.sendSms("http://xtx.telhk.cn:8080/sms.aspx", "send", "6366", "a10474", "753951", mobile, "【美嘉影城】" + content);
		String send = SmsClientSend.sendSms("http://121.52.212.233:7890/sms.aspx", "send", "6366", "801056", "H7nZz8", mobile, "【美嘉影城】" + content);
		try {
			String result = new String(send.getBytes("UTF-8"));
			if (result.contains("success")) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 美嘉短信渠道发送营销短信的渠道=====营销短信渠道=============
	 * @param mobile
	 * @param content
	 * @return
	 */
	public static boolean mjSendSmsYX(String mobile, String content) {
		//String send = SmsClientSend.sendSms("http://xtx.telhk.cn:8080/sms.aspx", "send", "6367", "y00494", "aa4220521", mobile, "【美嘉影城】" + content + "退订回T");
		String send = SmsClientSend.sendSms("http://121.52.212.233:7890/sms.aspx", "send", "6367", "801056", "H7nZz8", mobile, "【美嘉影城】" + content + "退订回T");
		try {
			String result = new String(send.getBytes("UTF-8"));
			if (result.contains("success")) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 美嘉短信渠道发送短息
	 * @param mobile
	 * @param content
	 * @return
	 */
	public static boolean mjSendSms2(String mobile, String content) {
		//String send = SmsClientSend.sendSms("http://xtx.telhk.cn:8080/sms.aspx", "send", "6366", "y00494", "aa4220521", mobile, "【美嘉影城】" + content + "退订回T");
		String send = SmsClientSend.sendSms("http://121.52.212.233:7890/sms.aspx", "send", "6366", "801056", "H7nZz8", mobile, "【美嘉影城】" + content + "退订回T");
		try {
			String result = new String(send.getBytes("UTF-8"));
			System.out.println(result);
			if (result.contains("success")) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 唐阁短信渠道发送短息
	 * @param mobile
	 * @param content
	 * @return
	 */
	public static boolean tgsendSms(String mobile, String msg) {
		try {
			String url = "http://222.73.117.158/msg/HttpBatchSendSM";
			String account = "Thyc88_Tangoyizhuang";
			String pswd	   = "TangGe123";
			boolean needstatus = true;
			Map<String, String> params = new HashMap<String, String>();
			params.put("account", 		account);
			params.put("pswd", 			pswd);
			params.put("mobile", 		mobile);
			params.put("needstatus", 	String.valueOf(needstatus));
			params.put("msg", 			msg);
			params.put("product", 		null);
			params.put("extno", 		null);
			String result = HttpUtil.doGet(url, params);
			if (result.contains(",0")) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 美凯龙短信渠道发送短息
	 * @param mobile
	 * @param content
	 * @return
	 */
	public static boolean mklsendSms(String mobile, String msg) {
		try {
			String url = "http://222.73.117.158/msg/HttpBatchSendSM";
			String account = "Thyc88_HongXing";
			String pswd	   = "HongXing123";
			boolean needstatus = true;
			Map<String, String> params = new HashMap<String, String>();
			params.put("account", 		account);
			params.put("pswd", 			pswd);
			params.put("mobile", 		mobile);
			params.put("needstatus", 	String.valueOf(needstatus));
			params.put("msg", 			msg);
			params.put("product", 		null);
			params.put("extno", 		null);
			String result = HttpUtil.doGet(url, params);
			if (result.contains(",0")) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 华谊短信渠道发送短息
	 * @param mobile
	 * @param content
	 * @return
	 */
	public static boolean hysendSms(String mobile, String content) {
		//发送POST请求
		try {
			content+="【华谊兄弟影城】";
			String sname = "dlhyxd01";
			String spwd = "bj654321";
			String sprdid = "1012818";
			String postUrl = "http://cf.51welink.com/submitdata/service.asmx/g_Submit?";
			String postData = "sname="+sname+"&spwd="+spwd+"&scorpid=&sprdid="+sprdid+"&sdst="+mobile+"&smsg="+java.net.URLEncoder.encode(content,"utf-8");
			URL url = new URL(postUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setUseCaches(false);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Length", "" + postData.length());
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(postData);
			out.flush();
			out.close();
			//获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return false;
			}
			//获取响应内容体
			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			if(result != null && result.contains("<State>0</State>")){
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return false;
	}
	
	
	
	/**
	  * 创蓝短信渠道发送短息
	 * @param mobile 手机号
	 * @param content 内容
	 * @param account 用户名
	 * @param pswd 密码
	 * @return
	 */
	public static boolean CLsendSms(String url,String mobile, String msg,String account,String pswd) {
		try {
//			String url = "http://222.73.117.158/msg/HttpBatchSendSM";
			boolean needstatus = true;
			Map<String, String> params = new HashMap<String, String>();
			params.put("account", 		account);
			params.put("pswd", 			pswd);
			params.put("mobile", 		mobile);
			params.put("needstatus", 	String.valueOf(needstatus));
			params.put("msg", 			msg);
			params.put("product", 		null);
			params.put("extno", 		null);
			String result = HttpUtil.doGet(url, params);
			if (result.contains(",0")) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 创蓝营销短信发送短息
	 * @param mobile 手机号
	 * @param content 内容
	 * @param account 用户名
	 * @param pswd 密码
	 * @return
	 */
	public static boolean CLsendSmsYX(String mobile, String msg,String account,String pswd) {
		try {
			String path = "http://smssh1.253.com/msg/send/json";
			Map<String, String> params = new HashMap<String, String>();
			params.put("account", 		account);
			params.put("password", 			pswd);
			params.put("phone", 		mobile);
			params.put("report", 	"true");
			params.put("msg", 			msg);
			
			JSONObject jo = JSONObject.fromObject(params);
			String param = jo.toString();
			URL url = new URL(path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");// 提交模式
			httpURLConnection.setConnectTimeout(10000);//连接超时 单位毫秒
			httpURLConnection.setReadTimeout(2000);//读取超时 单位毫秒
			// 发送POST请求必须设置如下两行
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.connect();
			OutputStream os=httpURLConnection.getOutputStream();
			os.write(param.getBytes("UTF-8"));
			os.flush();
			int httpRspCode = httpURLConnection.getResponseCode();
			if (httpRspCode == HttpURLConnection.HTTP_OK) {
				StringBuilder sb = new StringBuilder();
				// 开始获取数据
				BufferedReader br = new BufferedReader(
						new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
				if(sb.toString().contains("\"code\":\"0\"")){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 行天下短信平台
	 * 行业短信
	 * @param mobile
	 * @param content
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean XTXSendSms(String url,String mobile, String content,String port,String name,String psw,String signatures) {
		//String send = SmsClientSend.sendSms("http://xtx.telhk.cn:8080/sms.aspx", "send", port, name, psw, mobile, "【美嘉影城】" + content);
		//String send = SmsClientSend.sendSms("http://121.52.212.233:7803/sms", "send", port, name, psw, mobile, "【美嘉影城】" + content);
		String send = SmsClientSend.sendSms(url, "send", port, name, psw, mobile, signatures + content);
		try {
			String result = new String(send.getBytes("UTF-8"));
			logger.error("美嘉发送短信返回数据========="+result);
			Map<String, String> map =XMLUtil.doXMLParse(result);
			if (map.get("message").equals("OK")) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 美嘉营销短信
	 * @param mobile
	 * @param content
	 * @return
	 */
	
	public static boolean XTXSendSmsYX(String url,String mobile, String content,String port,String name,String psw,String countNumber,String signatures) {
		//String send = SmsClientSend.sendSms("http://xtx.telhk.cn:8080/sms.aspx", "send", "6367", "y00494", "aa4220521", mobile, "【美嘉影城】" + content + "退订回T",countNumber);
		//String send = SmsClientSend.sendSms("http://121.52.209.124:8888/sms.aspx", "send", "6367", "y00494", "aa4220521", mobile, "【美嘉影城】" + content + "退订回T",countNumber);
		String send = SmsClientSend.sendSms(url, "send", port, name, psw, mobile, signatures + content + "退订回T",countNumber);
		try {
			String result = new String(send.getBytes("UTF-8"));
			if (result.contains("success")) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean XTXSendSmsYX(String mobile, String content,String port,String name,String psw) {
		//String send = SmsClientSend.sendSms("http://xtx.telhk.cn:8080/sms.aspx", "send", "6367", "y00494", "aa4220521", mobile, "【美嘉影城】" + content + "退订回T",countNumber);
		String send = SmsClientSend.sendSms("http://121.52.209.124:8888/sms.aspx", "send", "6367", "y00494", "aa4220521", mobile, "【美嘉影城】" + content + "退订回T");
		try {
			String result = new String(send.getBytes("UTF-8"));
			if (result.contains("success")) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
     * 反射调用方法
     * @param newObj 实例化的一个对象
     * @param methodName 对象的方法名
     * @param args 参数数组
     * @return 返回值
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean invokeMethod(String methodName, Object[] args)throws Exception {
    	boolean state = true;
        Class ownerClass = new SmsUtils().getClass();
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = ownerClass.getMethod(methodName, argsClass);
        state = (boolean) method.invoke(ownerClass, args);
        return state;
    }
	
    
    public boolean sendMessage(String methodName, String mobile, String msg,String type) {
		boolean b = true;
		Object[] argsa = new Object[]{mobile,msg};
		try {
			b = invokeMethod(methodName,argsa);
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}
	
    
    public boolean sendMessage(String methodName, String mobile, String msg) {
		boolean b = true;
		Object[] argsa = new Object[]{mobile,msg};
		try {
			b = invokeMethod(methodName,argsa);
		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		}
		return b;
	}
    
    
    /**
	 * 华谊短信渠道发送短息
	 * @param mobile
	 * @param content
	 * @return
	 */
    
    public static boolean hysendSms(String postUrl,String mobile, String content,String sprdid,String sname,String spwd,String signatures) {
    	//发送POST请求
        try {
//        	String sname = "dlhyxd01";
//        	String spwd = "bj654321";
//        	String sprdid = "1012818";
//        	content+="【华谊兄弟影城】";
//        	String postUrl = "http://cf.51welink.com/submitdata/service.asmx/g_Submit";
        	content+=signatures;
        	String postData = "sname="+sname+"&spwd="+spwd+"&scorpid=&sprdid="+sprdid+"&sdst="+mobile+"&smsg="+java.net.URLEncoder.encode(content,"utf-8");
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();
            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return false;
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            if(result != null && result.contains("<State>0</State>")){
            	return true;
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
    
    
    /**
   	 * 华谊短信渠道发送短息
   	 * @param mobile
   	 * @param content
   	 * @return
   	 */
       public static boolean hysendSmsYX(String mobile, String content,String sprdid,String sname,String spwd) {
       	//发送POST请求
           try {
//           	String sname = "dlhyxd01";
//           	String spwd = "bj654321";
//           	String sprdid = "1012818";
           	String postUrl = "http://cf.51welink.com/submitdata/service.asmx/g_Submit?";
           	String postData = "sname="+sname+"&spwd="+spwd+"&scorpid=&sprdid="+sprdid+"&sdst="+mobile+"&smsg="+java.net.URLEncoder.encode(content,"utf-8");
               URL url = new URL(postUrl);
               HttpURLConnection conn = (HttpURLConnection) url.openConnection();
               conn.setRequestMethod("POST");
               conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
               conn.setRequestProperty("Connection", "Keep-Alive");
               conn.setUseCaches(false);
               conn.setDoOutput(true);
               conn.setRequestProperty("Content-Length", "" + postData.length());
               OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
               out.write(postData);
               out.flush();
               out.close();
               //获取响应状态
               if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                   return false;
               }
               //获取响应内容体
               String line, result = "";
               BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
               while ((line = in.readLine()) != null) {
                   result += line + "\n";
               }
               in.close();
               if(result != null && result.contains("<State>0</State>")){
               	return true;
               }
           } catch (IOException e) {
               e.printStackTrace(System.out);
           }
           return false;
       }
    
    
	public static void main(String[] args) {
		System.out.println(CLsendSmsYX("13439786998", "送您一张券", "N223354_N2414003", "1ZRd4MSmf5735a"));
	}

}
