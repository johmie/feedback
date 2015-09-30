<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="layout">
    <tiles:putAttribute name="content">
        <h1>${page.title}</h1>
        <c:forEach var="question" items="${page.questions}">
            <c:set var="question" value="${question}" scope="request"/>
            <jsp:include page="question.jsp"></jsp:include>
        </c:forEach>
    </tiles:putAttribute>
</tiles:insertDefinition>