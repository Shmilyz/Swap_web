package com.shmilyz.graduation.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.shmilyz.graduation.bean.UserInfo;
import com.shmilyz.graduation.others.Msg;
import com.shmilyz.graduation.others.Sms.SmsMethod;
import com.shmilyz.graduation.service.SmsService;
import com.shmilyz.graduation.service.UserService;
import com.shmilyz.graduation.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * Created by Shmily_Z on 2017/9/9.
 */
@Controller
@SessionAttributes(value = "username")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SmsService smsService;

    @ResponseBody
    @RequestMapping(value = "/code",method = RequestMethod.POST)
    public Msg userSign(@RequestParam("userName") String userName, @RequestParam("userPass") String userPass) throws Exception{
        String code= String.valueOf((int)((Math.random()*9+1)*100000));
        //发短信
        SendSmsResponse response = smsService.sendSms(userName,code);

//
        if (response.getCode() != null && response.getCode().equals("OK")){

            return Msg.success().add("sms",code);


        }else {

            return Msg.fail();

        }

    }


    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Msg userLogin(@RequestParam("userName") String userName, @RequestParam("userPass") String userPass, Map<String,Object> map){
        String userPass_md5= MD5.getMD5(userPass);
        System.out.println("结果---------"+userPass_md5);
        List<UserInfo> result_list=userService.loginUser(userName,userPass_md5);
        if (result_list.size()==0){
            map.clear();
            map.put("username","");
            return Msg.fail();


        }else {
            map.clear();
            map.put("username",result_list.get(0).getUserName());
            return Msg.success();


        }
    }





    @RequestMapping(value = "/a",method = RequestMethod.GET)
    public String  a(){

        return "a";
    }


}
