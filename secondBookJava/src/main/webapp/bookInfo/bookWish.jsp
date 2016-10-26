<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no"/>
<title>书籍详情</title>
<!-- <link rel="stylesheet" href="/semantic/semantic.min.css"/> -->
<link rel="stylesheet" href="/animate/animate.css"/>
<link rel="stylesheet" href="/stylesheets/bookInfo.css"/>
<link rel="stylesheet" href="/unslider/dist/css/unslider.css"/>
<link rel="stylesheet" href="/unslider/dist/css/unslider-dots.css"/>
<script type="text/javascript" src="/jquery/dist/jquery.js"></script>
<script type="text/javascript" src="/zepto/zepto.js"></script>
<script type="text/javascript" src="/js/bookInfo.js"></script>
<script type="text/javascript" src="/unslider/dist/js/unslider-min.js"></script>
<script>
</script>
</head>
<body>
	<div class="first">
	<%--   <s:if test="%{avatar!=null)"> --%>
	    <img src="<s:property value='avatar'/>"/>
    <%-- 	  </s:if>  --%>
	        <table>
	            <tr>
	                <td><s:property value="userName"/></td>
	                <td class="right_cell">
                   		    <s:property value="address"/>
	                </td>
	            </tr>
	              <tr>
	                <td>
	                		<s:property value="days"/>天前
	                </td>
	                <td class="right_cell" style="color:red">求购</td>
	            </tr>
	        </table>
        <h3 class="book_name"><s:property value="bookName"/></h3>
        <p class="description"><s:property value="bookDescription"/></p>
	</div>
	
	<div class="second">
    	<div class="unslider">
    	    <div class="automatic-slider"  style="position:relative; overflow:hidden">
		    	    <ul class="unslider-wrap" id="unslider-wrap">
		    	    	<s:iterator value="images">
		    	    	    <li><img src="<s:property />"/></li>
		    	    	</s:iterator>
		    	    </ul>
		   </div> 
    	</div>
	</div>
	
	<div class="third">
	   <ul>
	       <li>
	       		<s:if test="%{ISBN}">
	       			ISBN:&nbsp;&nbsp;<s:property value="ISBN"/>
       			</s:if>
	       </li>
	       <li><s:property value="press"/>
	       <s:if test="author">
	       ，<s:property value="author"/>
	       </s:if>
	       </li>
	       <li>
	       		<s:if test="%{publishYear}">
	       			<s:property value="publishYear"/>出版
    			</s:if>
	       </li>
	   </ul>
	</div>
	
	<div class="imageContainer" style="display:none">
	  <!--   <img id="#bigImg"/> -->
	  <canvas id="canvasImg" ></canvas>
	</div>
</body>
</html>