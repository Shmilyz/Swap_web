<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>田俊哲毕业项目设计</title>
    <%--获取我们的APP_PATH--%>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>

    <link
            href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
            rel="stylesheet">
    <link rel="stylesheet" href="${APP_PATH }/static/css/index/main.css">
    <link rel="stylesheet" href="${APP_PATH }/static/css/index/responsive.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
</head>
<body style="text-align:center;">

<!--header section -->
<section class="banner" role="banner" >
    <div class="banner-area">
        <!-- overlay -->
        <div class="banner-area-gradient" ></div>
        <!-- style="height: 100%"待解决 -->
        <div class="inner-bg" style="height: 100%">
            <div class="container"  id="page_first_show">
                <div class="col-md-12"  >
                    <div class="banner-text" >
                        <h1 style="color: #ffffff;margin-top: 130px" >&nbsp;SWAP</h1>
                        <!--Countdown -->
                        <%--<div id="countdown"></div>--%>

                        <div class="row" style="margin-top: 250px;" >

                            <a class="logo" href="#" style="margin-left: 15px" id="page_login" >登录</a>
                            <a class="logo" href="#" style="margin-right: 15px" id="page_sign">注册</a>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!--header section -->
<section id="intro" class="section intro">
    <div class="container">
        <div class="row" >
            <div style="text-align: center" >
                <!-- intro -->
                <img src="static/images/code_one.jpg" class="img-rounded" style="max-height: 80px;max-width: 80px;margin-right: 60px;display:inline-block">
                <img src="static/images/code_two.png" class="img-rounded" style="max-height: 80px;max-width: 80px; display:inline-block">


                </h6>


            </div>

        </div>
    </div>
</section>


<!-- JS files-->
<script type="text/javascript"
        src="${APP_PATH }/static/js/jquery-3.2.1.min.js"></script>
<script src="${APP_PATH }/static/js/index/jquery.backstretch.min.js"></script>
<script src="${APP_PATH }/static/js/index/jquery.countdown.js"></script>
<script type="text/javascript" src="${APP_PATH }/static/js/index/jquery.subscribe.js"></script>
<script src="${APP_PATH }/static/js/index/main.js"></script>
<script
        src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>





<script type="text/javascript">

    var InterValObj; //timer变量，控制时间
    var count = 60; //间隔函数，1秒执行
    var curCount;//当前剩余秒数
    var sign_in=$("<div  style='margin-top:50px'><a class='logo' href='#' id='sign_in' style='color: #FFFFFF'>验证</a></div>");
    var  add_input_code=  "<input type='text' class='form-control input-lg' id='Code' placeholder='请输入验证码' style='background: inherit;margin-top: 30px;color: #FFFFFF;font-size: 15px' name='userCode'/>";
    var sign_yz=$("<div  style='margin-top:50px'><a class='logo' href='#' id='sign_yz' style='color: #FFFFFF'>验证</a></div>");
    var result_code;
    $("#page_login").click(function () {

        var page_first_show=$("#page_first_show");
        var login_content=$(" <h4 style='color: #ffffff;margin-top: 150px;font-weight: 300'>用户登录</h4>" +"<form id='get_info'>"+
            "<input type='text' class='form-control input-lg' id='InputUser' placeholder='用户名/手机号/邮箱/身份证' style='background: inherit;margin-top: 60px;color: #FFFFFF;font-size: 15px' name='userName'/>" +
            "<input type='password' class='form-control input-lg' id='InputPass' placeholder='请输入您的密码' style='background: inherit;margin-top: 30px;color: #FFFFFF;font-size: 15px'  name='userPass'/>"+"</form>"
        );
        var login_content_div=$("<div id='login_content_div'></div>").addClass("form-group").append(login_content);
        var login_in=$("<div  style='margin-top:50px'><a class='logo' href='#' id='login_in' style='color: #FFFFFF'>登录</a></div>");
        var login_content_div_boot=$("<div></div>").addClass("col-md-4 col-md-offset-4").append(login_content_div).append(login_in);
        page_first_show.empty();
        page_first_show.append(login_content_div_boot);
        login_check();
        return false;

    });
    $("#page_sign").click(function () {

        var page_first_show=$("#page_first_show");
        var sign_content=$("<h4 style='color: #ffffff;margin-top: 150px;font-weight: 300'>用户注册</h4>" +"<form id='get_info_sign'>"+
            "<input type='text' class='form-control input-lg' id='InputUser' placeholder='手机号' style='background: inherit;margin-top: 60px;color: #FFFFFF;font-size: 15px' name='userName'/>" +
            "<input type='password' class='form-control input-lg' id='InputPass' placeholder='请输入您的密码' style='background: inherit;margin-top: 30px;color: #FFFFFF;font-size: 15px'  name='userPass'/>"+"</form>"
        );
        var sign_content_div=$("<div id='sign_content_div'></div>").addClass("form-group").append(sign_content);
//        var sign_in=$(" <div class='row' style='margin-top: 50px;' > <a class='logo' href='#' style='margin-left: 15px' id='page_sign' >登录</a> <a class='logo' href='#' style='margin-right: 15px' id='page_sign'>注册</a> </div>");


        var sign_content_div_boot=$("<div></div>").addClass("col-md-4 col-md-offset-4").append(sign_content_div).append(sign_in).append(sign_yz);
        page_first_show.empty();
        page_first_show.append(sign_content_div_boot);
        $("#sign_yz").hide();
        sign_check(sign_in,sign_content_div_boot);
        return false;

    });
    function  login_check() {


        $("#login_in").click(function () {
//                alert($("#get_info").serialize());
            $.ajax({
                url:"${APP_PATH}/login",
                type:"POST",
                data:$("#get_info").serialize(),
                success:function (result) {
                    if(result.code==100){
                        $("#login_h6_false").remove();
                        window.location.href="${APP_PATH }/a";
                    }else {
                        var login_h6_false=$("<h6 id='login_h6_false' style='color: #ffffff;margin-top:10px'>你的账号或密码有误</h6>");
                        $("#login_h6_false").remove();
                        $("#login_content_div").append(login_h6_false);
                    }
                }
            });
            return false;
        });




    }
    function sign_check(sign_in,sign_content_div_boot) {

        $("#sign_in").click(function () {

            var isin=$("#get_info_sign #Code").length

            if (isin>0){

            }
            else{

                var all_input=$("#get_info_sign")
                $("#get_info_sign #Code").remove();
                sign_in.hide();
                $.ajax({
                    url:"${APP_PATH}/code",
                    type:"POST",
                    data:$("#get_info_sign").serialize(),
                    success:function (result) {

                        if (result.code==100){
                            result_code=result.entend.sms;
                        }else {

                            alert("您今天发送次数过多!")

                        }

                    }

                });
                all_input.append(add_input_code);
                $("#sign_yz").show();
                var sign_obj=$("#sign_yz");
                sign_yz_click(sign_obj);



                sendMessage(sign_obj);

            }


            return false;
        });


    }

    function sign_yz_click(sign_obj) {

        sign_obj.click(function () {

            var Code_val=$("#get_info_sign #Code").val().trim();
            if (Code_val!=''&&Code_val!=null){


                $.ajax({
                    url:"${APP_PATH}/sign",
                    type:"POST",
                    data:$("#get_info_sign").serialize(),
                    success:function (result) {

                        if (result.code==100){
                            window.location.href="${APP_PATH }/a";
                        }else {

                            var sign_h6_false_code=$("<h6 id='sign_h6_false_code' style='color: #ffffff;margin-top:10px'>您的验证码输入有误</h6>");
                            $("#sign_h6_false_code").remove();
                            $("#sign_h6_false").remove();
                            $("#sign_content_div").append(sign_h6_false_code);


                        }

                    }

                });


            }
            else {

                var sign_h6_false=$("<h6 id='sign_h6_false' style='color: #ffffff;margin-top:10px'>请您填写验证码后再验证</h6>");
                $("#sign_h6_false_code").remove();
                $("#sign_h6_false").remove();
                $("#sign_content_div").append(sign_h6_false);

            }






        });

    }


    function sendMessage(obj) {

        curCount=count;
        $("#sign_yz").text("点击注册(" + curCount + ")");
        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次


    }
    function SetRemainTime() {

        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#sign_yz").hide();
            $("#Code").remove();
            sign_in.show();
        }
        else {
            curCount--;
            $("#sign_yz").text("点击注册(" + curCount + ")");
        }


    }
</script>
</body>
</html>



