package com.ingcore.common.utils;

import java.util.List;
import java.util.Map;

/**
 * ClassName:InitCinemaNameList
 *
 * @author bilaoye
 * @create 2018-01-16 16:42
 */
public class InitCinemaNameList {


    public  static  String initCinemaList(List<String> cinemaCodes, Map cinemaMap){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<cinemaCodes.size();i++){
            if(i>3){
                break;//影城名称暂时只三个
            }
            sb.append("<p>").append(cinemaMap.get(cinemaCodes.get(i))).append("</p>");

        }
        return  sb.toString();
    }
}