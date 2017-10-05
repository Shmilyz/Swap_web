import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Shmily_Z on 2017/10/5.
 *
 * 我们需要
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {

//获取Springmvc的ioc容器，@Autowired只能获取容器里面的，为了能获取到获取Springmvc的ioc容器，我们增加注解@WebAppConfiguration
    @Autowired
WebApplicationContext context;

//虚拟mvc请求，获得请求结果。
    MockMvc mockMvc;

    @Before
    public  void initMockMvc(){

        mockMvc=MockMvcBuilders.webAppContextSetup(context).build();

    }

@Test
    public  void test() throws Exception {

        MultiValueMap<String, String> map= new LinkedMultiValueMap();
        map.add("userName","15522902120");
        map.add("userPass","9541");
//        模拟发送请求拿到返回值1
        MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post("/login").params(map)).andReturn();
    //获取我们的Response，紧接着获取我们的content
    MockHttpServletResponse response= result.getResponse();
    System.out.println(response.getContentAsString());

    }


}
