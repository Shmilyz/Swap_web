import com.shmilyz.graduation.utils.VerifyUtils;
import net.sf.jsqlparser.expression.UserVariable;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shmily_Z on 2017/9/9.
 */
public class UsetVerifyTest {

/*
* verify()方法测试用户登录使用的账号为什么。
* */
@Test
    public void verify(){

      /*  String name="12011319960324041";
        String str=name.substring(0,1);
        System.out.println(name);
        Pattern pattern = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
//  [0-9]*      第一位为数字
//  ^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$ 邮箱
//    ^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$ 身份证
//    ^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$ 手机号码
    Matcher isNum = pattern.matcher(name);*/
    boolean a= VerifyUtils.email("836811a@qq.com");
    boolean b= VerifyUtils.email("83a@qq.com");
        if (a){
            System.out.println("1");
        }

    }


}
