package com.ingcore.common.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.ingcore.common.constants.ResultConstants;

import java.util.Map;

/**
 * ClassName:MemberTagUtils
 *
 * @author bilaoye
 * @create 2017-12-26 13:50
 */
public class MemberTagUtils {

    public static  String getSqlByTagCondition(Map<String,String> map){
        StringBuffer sbsql= new StringBuffer();
        if(map!=null){
          String membertag=  map.get("tag");
            switch (membertag){
                case ResultConstants.ACTOR_TAG:
                    sbsql.append("SELECT  DISTINCT(member_id) as member_code FROM  t_orders  t1 INNER JOIN t_film_info  tm ON t1.film_custom_code=tm.film_key");
                    sbsql.append("  where t1.cinema_code in("+map.get("cinemaCodes")+") and  tm.film_leading LIKE  '%"+map.get("tagValue").split("_")[0]+"%'");
                  break;
                case ResultConstants.SEX_TAG:
                    sbsql.append("SELECT member_code   FROM t_loyalty_member where member_owner in("+map.get("cinemaCodes")+") and   member_sex="+map.get("tagValue").split("_")[0]);
                  break;
                case ResultConstants.AGE_TAG:
                    Map birthdayMap=getBirthday(map.get("tagValue"));
                    sbsql.append("SELECT member_code   FROM t_loyalty_member where  member_owner in("+map.get("cinemaCodes")+") and  member_birthday >='"+birthdayMap.get("startDate")+"' and member_birthday <='"+birthdayMap.get("endDate")+"'");
                    break;
                case ResultConstants.CARD_TAG:
                    sbsql.append("SELECT  member_code FROM t_card_sale where buy_cinema_code in("+map.get("cinemaCodes")+") and del_flag=0 and member_code is NOT null");

                    break;
                case ResultConstants.MOVIE_TAG:
                    sbsql.append("SELECT  DISTINCT(member_id) FROM  t_orders  where  cinema_code in("+map.get("cinemaCodes")+") and  film_custom_code='"+map.get("tagValue").split("_")[0]+"'");
                    break;
                case ResultConstants.MOVIE_TYPE_TAG:
                    sbsql.append("SELECT  DISTINCT(t1.member_id)  FROM t_orders t1 INNER JOIN  t_film_info t2 ")
                          .append( "on t1.film_custom_code=t2.film_key where t1.cinema_code in("+map.get("cinemaCodes")+") and  t2.film_type="+map.get("tagValue").split("_")[0]+" and t1.order_status=1001");
                  break;
                case ResultConstants.VIEW_TYPE_TAG:
                    String day="";
                    if(StringUtils.equals("1",map.get("tagValue").split("_")[0])){
                        day=" WEEKDAY  (show_start_time)   between 6 and 7";//非工作日
                    }
                    if(StringUtils.equals("2",map.get("tagValue").split("_")[0])){
                        day=" WEEKDAY  (show_start_time)   between 1 and 5";//工作日
                    }
                    sbsql.append("select  DISTINCT(member_id) from t_orders where  order_status=1001 and del_flag=0 and  cinema_code in("+map.get("cinemaCodes")+") and "+day+"   ORDER BY show_start_time DESC");
                  break;
                case ResultConstants.VIEW_TIME_TAG:
                    sbsql.append("SELECT  DISTINCT(member_id)  FROM t_orders where  cinema_code in("+map.get("cinemaCodes")+")  and show_start_time>=CONCAT(CURDATE(),' "+map.get("tagValue").split("_")[0].split("-")[1]+"') ")
                            .append("and show_start_time<=CONCAT(CURDATE(),' "+map.get("tagValue").split("_")[0].split("-")[1]+"') and del_flag=0 and order_status=1001");
                  break;
                case ResultConstants.SELL_TYPE_TAG:
                    sbsql.append("SELECT DISTINCT(member_id) FROM t_orders t INNER JOIN t_order_detail  t1 ON t.order_code=t1.order_code where t.order_status=1001 AND TYPE IN( 2,3) and t.cinema_code IN("+map.get("cinemaCodes")+")");
                    sbsql.append("and t1.sell_id="+map.get("tagValue").split("_")[0]+" and t.del_flag=0");
                  break;
                case ResultConstants.PAY_TYPE_TAG:
                    sbsql.append("SELECT  DISTINCT(member_id) FROM t_orders where cinema_code in("+map.get("cinemaCodes")+") and pay_way="+map.get("tagValue").split("_")[0]+" and  cinema_code in("+map.get("cinemaCodes")+")");
                  break;
                case ResultConstants.DIRECTOR_TAG:
                    sbsql.append("SELECT  DISTINCT(member_id) as member_code FROM  t_orders  t1 INNER JOIN t_film_info  tm ON t1.film_custom_code=tm.film_key ");
                    sbsql.append("where  t1.cinema_code in("+map.get("cinemaCodes")+") and  tm.film_writer like '%"+map.get("tagValue").split("_")[0]+"%'");
                    break;
                case ResultConstants.SCREENWRITER_TAG:
                    sbsql.append("SELECT  DISTINCT(member_id) as member_code FROM  t_orders  t1 INNER JOIN t_film_info  tm ON t1.film_custom_code=tm.film_key ");
                    sbsql.append("where t1.cinema_code in("+map.get("cinemaCodes")+") and  tm.film_regisseur like '%"+map.get("tagValue").split("_")[0]+"%'");
                    break;
                case ResultConstants.SELLSINGLENUM_TAG:
                    sbsql.append("SELECT  t1.member_id FROM  t_orders t1  " )
                          .append(  "INNER JOIN  (SELECT  order_code FROM t_order_detail where  detail_type=2 and  cinema_code  in("+map.get("cinemaCodes")+") " )
                          .append(  "GROUP BY order_code  HAVING SUM(pay_amount)> "+map.get("tagValue").split("_")[0]+") t2 ON t1.order_code=t2.order_code where t1.order_status=1001");

                    break;
                case ResultConstants.SELL_NUM_TAG:
                    sbsql.append("SELECT member_id FROM t_orders where type in(2,3) and order_status=1001 and cinema_code in("+map.get("cinemaCodes")+") GROUP BY member_id HAVING COUNT(member_id)>="+map.get("tagValue").split("_")[0]+"");
                 break;
                case ResultConstants.CARD_TYPE_TAG:
                    sbsql.append("SELECT  member_code FROM t_card_sale where buy_cinema_code in("+map.get("cinemaCodes")+") and   card_id='"+map.get("tagValue").split("_")[0]+"' and del_flag=0 and member_code is NOT null");
                    break;
                case ResultConstants.VIEW_NUM_TAG:
                    sbsql.append("SELECT DISTINCT(member_id) FROM t_orders where    order_status=1001 and cinema_code in("+map.get("cinemaCodes")+") GROUP BY member_id HAVING  COUNT(member_id)>"+map.get("tagValue").split("_")[0]+"");
                    break;
                case ResultConstants.MEMBERACTIVE_TAG:
                    sbsql.append("SELECT  member_code FROM  t_loyalty_member  where  last_consume_datetime<=CONCAT(DATE_SUB(curdate(),INTERVAL +"+map.get("tagValue").split("_")[0]+" DAY),' 23:59:59')");
                    break;

            }
        }
        return  sbsql.toString();
    }

    private static Map getBirthday(String tagValue) {
        Map map= Maps.newHashMap();
        switch (tagValue.split("_")[0]){
            case "10":
                map.put("startDate","2011-01-01");
                map.put("endDate","2020-12-31");
                break;
            case "00":
                map.put("startDate","2001-01-01");
                map.put("endDate","2010-12-31");
                break;
            case "90":
                map.put("startDate","1991-01-01");
                map.put("endDate","2000-12-31");
                break;
            case "80":
                map.put("startDate","1981-01-01");
                map.put("endDate","1990-12-31");
                break;
            case "70":
                map.put("startDate","1971-01-01");
                map.put("endDate","1980-12-31");
                break;
            case "60":
                map.put("startDate","1961-01-01");
                map.put("endDate","1970-12-31");
                break;
            case "50":
                map.put("startDate","1951-01-01");
                map.put("endDate","1960-12-31");
                break;
        }
        return  map;
    }
}