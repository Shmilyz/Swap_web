package com.shmilyz.graduation.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.shmilyz.graduation.bean.UserInfo;
import com.shmilyz.graduation.others.Msg;
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
@SessionAttributes(value = {"username","userid"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SmsService smsService;

    private String result_code;
    @ResponseBody
    @RequestMapping(value = "/code",method = RequestMethod.POST)
    public Msg userSms(@RequestParam("userName") String userName, @RequestParam("userPass") String userPass) throws Exception{
        String code= String.valueOf((int)((Math.random()*9+1)*100000));
        //发短信
        SendSmsResponse response = smsService.sendSms(userName,code);
        System.out.println("验证码返回结果----------"+response.getCode());
        System.out.println("验证码的值----------"+code);
//        判断短信是否成功
        if (response.getCode() != null && response.getCode().equals("OK")){
            result_code=code;
            return Msg.success().add("sms",code);


        }else {

            return Msg.fail();

        }

    }

    @ResponseBody
    @RequestMapping(value = "/sign",method = RequestMethod.POST)
    public Msg userSign(@RequestParam("userName") String userName, @RequestParam("userPass") String userPass,@RequestParam("userCode") String userCode ,Map<String,Object> map){

        if (userCode.equals(result_code)){

            String userPass_md5= MD5.getMD5(userPass);
            System.out.println("结果---------"+userPass_md5);
            UserInfo userInfo=new UserInfo();
            userInfo.setUserPhone(userName);
            userInfo.setUserPass(userPass_md5);
            userInfo.setUserDate(new Date());
            userService.signUser(userInfo);
            Integer user_id=userInfo.getUserId();
            System.out.println("用户数据库唯一Id-------------"+user_id);
            if (user_id!=null){
                map.clear();
                map.put("userid",user_id);
                map.put("username",userName);
                return Msg.success();

            }else {

                return Msg.fail();
            }

        }else {

            return Msg.fail();

        }


    }


    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Msg userLogin(@RequestParam("userName") String userName, @RequestParam("userPass") String userPass, Map<String,Object> map){
        String userPass_md5= MD5.getMD5(userPass);
        System.out.println("结果---------"+userPass_md5);
        List<UserInfo> result_list=userService.getloginUser(userName,userPass_md5);
        if (result_list.size()==0){
            map.clear();
            map.put("username","");
            return Msg.fail();


        }else {
            map.clear();
            System.out.println("用户姓名-------"+result_list.get(0).getUserName());
            if (result_list.get(0).getUserName()==null){

                map.put("username",result_list.get(0).getUserPhone());

            }else {
                map.put("username",result_list.get(0).getUserName());

            }
            map.put("userid",result_list.get(0).getUserId());

            return Msg.success();


        }
    }





    @RequestMapping(value = "/a",method = RequestMethod.GET)
    public String  a(){

        return "a";
    }


}
