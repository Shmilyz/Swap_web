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

/**
 * Created by Shmily_Z on 2017/9/9.
 */

@Service
public class UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);


    public void signUser(UserInfo userInfo) {
        userInfoMapper.insertSelective(userInfo);
        Integer userid=userInfo.getUserId();

    }

/*
* 按照给定的userName和userPass去搜素是否存在符合的值，返回List<UserInfo>
*去判断是否查到对应的账号密码
* */
    public  List<UserInfo> getloginUser(String userName, String userPass){

        UserInfoExample employeeExample=new UserInfoExample();
        UserInfoExample.Criteria criteria=employeeExample.createCriteria();
        isNumeric(userName,criteria);
        criteria.andUserPassEqualTo(userPass);
        long count= userInfoMapper.countByExample(employeeExample);
        List<UserInfo> userInfo=userInfoMapper.selectByExample(employeeExample);

        logger.debug("登录验证方法loginUser()返回---");
        return userInfo;

    }

//    该方法判断输入账户的类型
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
