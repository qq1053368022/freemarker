<html>
<head>
    <title>登录</title>
    <style>.error{color:red;}</style>
    <script type="text/javascript" src="${request.contextPath}/js/jquery-2.1.1.min.js"></script>
</head>
<body>

<div class="error" id="loginError" ></div>
<div>
    用户名：<input type="text" name="username" id="username" ><br/>
    密码：<input type="password" name="password" id="password" ><br/>
    <input type="submit" onclick="login()" value="登录">
</div>
<script>
    function login() {
        $.ajax({
            url:'/onLogin',
            type:'post',
            data:'username='+ $("#username").val()+'&password='+ $("#password").val(),
            async : true,// 默认为true 异步
            error:function(){
                alert('error');
            },
            success:function(data){
                // alert(data);
                var data = $.parseJSON(data);

                if(data.msg=="success"){
                    window.location.href =data.url
                }else{
                    // alert(data.reason);
                    $("#loginError").html(data.returnStr);
                }

            }
        });

    }
</script>

</body>
</html>