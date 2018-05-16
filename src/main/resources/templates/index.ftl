<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<!-- 注释1 -->
<#--注释2-->
<@shiro.user>
    <h1>${name}</h1>
<h2>欢迎用户[<@shiro.principal/>]登陆</h2>
    <br>

    <#--PS:当引用不存在时-->
    <br>
    <h1>${nowTime}</h1>
    <br>
    <br>
    ${(5+8)/2}
    <br>

    <#--if指令--> <#if name=='World'>
            <h3 style="color: red;">${name} 优夺</h3>
    <#else>
            <h3 style="color: yellow;">不知</h3></#if> <br>
    <br>
    <#--list指令--> <#list lists as s>
            <li>${s}</li>
    </#list> <br>
    <#--list集合--> <#list ['a','b','c','d',1,2] as str>
            <li>${str}</li>
    </#list> <br>
    <#--map集合--> <#list TestMap?keys as key>
            <li>${key} ---- ${TestMap[key]}</li>
    </#list> <br>
</@shiro.user>
<br>
<!-- 使用shiro标签 -->
        <@shiro.guest>
            您当前是游客，<a href="${request.contextPath}/onLogin" >登录</a>
        </@shiro.guest>
</body>
</html>