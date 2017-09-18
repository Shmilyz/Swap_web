package com.shmilyz.graduation.service;

import com.shmilyz.graduation.bean.UserInfo;
import com.shmilyz.graduation.bean.UserInfoExample;
import com.shmilyz.graduation.dao.UserInfoMapper;
import com.shmilyz.graduation.utils.VerifyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shmily_Z on 2017/9/9.
 */

@Service
public class UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

/*
* 按照给定的userName和userPass去搜素是否存在符合的值，返回boolean
*返回true则代表登录失败，返回false则代表登录成功
* */
    public  List<UserInfo> loginUser(String userName,String userPass){

        UserInfoExample employeeExample=new UserInfoExample();
        UserInfoExample.Criteria criteria=employeeExample.createCriteria();
        isNumeric(userName,criteria);
        criteria.andUserPassEqualTo(userPass);
        long count= userInfoMapper.countByExample(employeeExample);
        List<UserInfo> userInfo=userInfoMapper.selectByExample(employeeExample);

        logger.debug("登录验证方法loginUser()返回---");
        return userInfo;

    }
    private void isNumeric(String str, UserInfoExample.Criteria criteria){
//        String first=str.substring(0,1);
        if (VerifyUtils.email(str)){

            criteria.andUserEmailEqualTo(str);


        }else {

            if (VerifyUtils.phone(str)){

                criteria.andUserPhoneEqualTo(str);
            }else {

                if (VerifyUtils.cid(str)){
                    criteria.andUserCardEqualTo(str);

                }else {
                    criteria.andUserNameEqualTo(str);

                }



        }


        }

    }


}
