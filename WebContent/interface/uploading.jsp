<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  
<meta charset="utf-8">
  
<title>文件上传</title>   
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
      
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<form action="/uploading" method="post"  enctype="multipart/form-data">

		文件名：<input type="test" name="name" value="" /> <br /> 
		设置照片: <input type="file" name="headerImg" value="" />

		<button type="submit">确认上传</button>
	</form>

</body>
</html>