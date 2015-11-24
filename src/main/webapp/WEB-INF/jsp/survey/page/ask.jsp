<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tiles:insertDefinition name="layout">
    <tiles:putAttribute name="content">
        <h1>${page.title}</h1>
        <form:form method="post" modelAttribute="pageModel" class="form-inline">
            <c:forEach var="question" items="${page.questions}">
                <c:set var="question" value="${question}" scope="request"/>
                <jsp:include page="../question.jsp"></jsp:include>
            </c:forEach>
            <spring:message code="label.next" var="labelNext"/>
            <input type="submit" class="btn btn-default pull-right" value="${labelNext}"/>
        </form:form>
    </tiles:putAttribute>
</tiles:insertDefinition>