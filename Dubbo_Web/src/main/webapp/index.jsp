<html>
<head>
<script type="text/javascript" src="js/Util.js"></script>
</head>
<body>
	<h2>Hello World!</h2>
	<button id='btn' name="btn" value="button" type="button" onclick="send()" />
	<div id='showUser'></div>
</body>
</html>
<script>
    function send(){
    	post("<%=request.getContextPath()%>/user.do", "name=mdj&password=mdj",show);
    }
    
    function show(data){
    	alert("show:"+data);
    }
</script>