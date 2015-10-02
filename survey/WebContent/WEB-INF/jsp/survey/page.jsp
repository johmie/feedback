<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<tiles:insertDefinition name="layout">
    <tiles:putAttribute name="content">
        <div class="page">
            <h1>${page.title}</h1>
            <form:form method="post" modelAttribute="surveyForm">
                <c:forEach var="question" items="${page.questions}">
                    <c:set var="question" value="${question}" scope="request"/>
                    <jsp:include page="question.jsp"></jsp:include>
                </c:forEach>
                <input type="submit" value="weiter"/>
            </form:form>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>