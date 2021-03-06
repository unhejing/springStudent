<%@ tag pageEncoding="UTF-8" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="fieldName" required="true" type="java.lang.String" %>
<%@ attribute name="selectedGradeId" required="false" type="java.lang.Object" %>
<%@ attribute name="selectedGrades" required="false" type="java.util.List"%>
<%@ attribute name="multiple" required="false" type="java.lang.Boolean" %>
<%@ attribute name="withNone" required="false" type="java.lang.Boolean" %>
<%@ attribute name="noneText" required="false" type="java.lang.String" %>
<%@ attribute name="fieldStyleClass" required="false" type="java.lang.String" %>

<%
    //request.setAttribute("selectedUserId_seq", ValueUtils.getValue((Integer) request.getAttribute("selectedUserId_seq")) + 1);
    //id="cs_${selectedUserId_seq}"cs_${selectedUserId_seq}
%>
<select
        <c:if test="${multiple}">
            multiple=""
        </c:if>
        class="${empty fieldStyleClass ? 'form-control chosen-select' : fieldStyleClass}" name="${fieldName}">
    <c:if test="${withNone}">
        <option value=""><spring:message code="${empty noneText ? 'common.selectNone' : noneText}"/></option>
    </c:if>
    <c:forEach items="${selectedGrades}" var="grade">
        <option value="${grade.id}" ${selectedGradeId == grade.id ? 'selected' : ''}>${grade.name}</option>
    </c:forEach>
</select>
