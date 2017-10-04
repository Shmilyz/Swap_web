package com.shmilyz.graduation.others.Sms;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

/**
 * Created by Shmily_Z on 2017/10/5.
 */
public class main {

    public static void main(String[] args) throws Exception {

        SmsMethod smsMethod=new SmsMethod();
        String code= String.valueOf((int)((Math.random()*9+1)*100000));




        //发短信
        SendSmsResponse response = smsMethod.sendSms("15522902120",code);
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());


    }

}
