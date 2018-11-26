<html>
<head>
    <script type="text/javascript" src="js/Util.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<button id='btn' name="btn" value="button" type="button" onclick="send()"/>
</body>
</html>
<script>
    function send(){
    	post("<%=request.getContextPath()%>/user.do","name=mdj");
    }
</script>