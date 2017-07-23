<%--
  Created by IntelliJ IDEA.
  User: 天亮就出发
  Date: 2017/3/13
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>完成评价</title>
</head>
<body>
    <c:if test="${!empty evaluationData}">
        开始时间: ${evaluationData.beginTime}<br>
        结束时间：${evaluationData.endTime}<br>
        休息时间：${evaluationData.relaxMinutes} 分钟<br>
        休息次数：${evaluationData.relaxTimes}<br>
        走神次数：${evaluationData.distractionTimes}<br>
    </c:if>
    <c:if test="${!empty distractionTimeList}">
        <c:forEach items="${distractionTimeList}" var="distractionTime">
            开始时间：${distractionTime.sTime}<br>
            结束时间：${distractionTime.eTime}<br>
        </c:forEach>
    </c:if>
</body>
</html>
