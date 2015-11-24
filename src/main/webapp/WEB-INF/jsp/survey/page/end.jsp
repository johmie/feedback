<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tiles:insertDefinition name="layout">
    <tiles:putAttribute name="content">
        <h1><spring:message code="content.end_headline"/></h1>

        <p><spring:message code="content.end_content"/></p>
    </tiles:putAttribute>
</tiles:insertDefinition>