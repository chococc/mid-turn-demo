var username=document.getElementById("username");
var password=document.getElementById("password");
var LoginButton = document.getElementById("LoginButton");
var UserNameText='';
function login(){
    if(username.length==0){
        alert("请输入用户名");
        username.focus();
        return false;
    }
    if(password.length==0){
        alert("请输入密码");
        password.focus();
        return false;
    }
    UserNameText=$("#username").val();
    var PasswordText=$("#password").val();

    var da={
        "username":UserNameText,
        "password":PasswordText,
    };
    commonAjaxPost(true,"/login_service",da,loginSuccess)
}

//登录成功回调
function loginSuccess(result){
    if (result.code == '200') {
        layer.msg(result.message, {icon:1});
        setCookie('isLogin','1');
        setCookie('userId',result.data.username);
    }else if(result.code=='201'){
        layer.msg("此用户不存在",{icon:2});
    }else if(result.code=='202'){
        layer.msg("密码错误",{icon:2});
    }
}

//回车触发事件
$(document).keydown(function(event){
    if(event.keyCode==13){
        login();
    }
});