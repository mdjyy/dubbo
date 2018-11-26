function getAjaxObject(){
	if(window.XMLHttpRequest){
		return  new XMLHttpRequest();
	}else if( window.ActiveXObject){
		return  new ActiveXObject();
	}else{
		alert("您的浏览器不支持XMLHttp");
	}
}

function post(url,data){
	var xmlhttp = getAjaxObject();
	xmlhttp.open("POST",url,true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(data);
	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState == 4){
			if(xmlhttp.status == 200){
				return xmlhttp.responseText;
			}else{
				alert("错误信息:"+ xmlhttp.statusText);
			}
		}
	}

}