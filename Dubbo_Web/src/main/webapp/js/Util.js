function getAjaxObject(){
	if(window.XMLHttpRequest){
		return  new XMLHttpRequest();
	}else if( window.ActiveXObject){
		return  new ActiveXObject();
	}else{
		alert("您的浏览器不支持XMLHttp");
	}
}

function post(url,data,fn){
	var xmlhttp = getAjaxObject();
	xmlhttp.open("POST",url,true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded;charset=UTF-8");
	//xmlhttp.setRequestHeader("Content-type","application/json;charset=UTF-8");//可以发送json格式字符串
	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState == 4){
			if(xmlhttp.status == 200){
				fn.call(this,xmlhttp.responseText);
			}else{
				alert("错误信息:"+ xmlhttp.statusText);
			}
		}
	}
	xmlhttp.send(data);
}