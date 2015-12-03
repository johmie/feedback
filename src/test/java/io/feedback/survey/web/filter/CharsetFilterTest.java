package io.feedback.survey.web.filter;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class CharsetFilterTest {

    private CharsetFilter charsetFilter;

    @Before
    public void setUp() {
        charsetFilter = new CharsetFilter();
    }

    @Test
    public void setEncoding_SomeEncoding_SameValueIsReturnedByGetEncoding() {
        String encoding = "ISO-8859-1";

        charsetFilter.setEncoding(encoding);

        assertEquals(encoding, charsetFilter.getEncoding());
    }

    @Test
    public void init_FilterConfigWithRequestEncoding_EncodingIsSetByFilterConfig() {
        String encoding = "ISO-8859-1";
        FilterConfig filterConfigMock = mock(FilterConfig.class);
        when(filterConfigMock.getInitParameter("requestEncoding")).thenReturn(encoding);

        try {
            charsetFilter.init(filterConfigMock);

            assertEquals(encoding, charsetFilter.getEncoding());
        } catch (ServletException servletException) {
            fail();
        }
    }

    @Test
    public void init_FilterConfigWithoutRequestEncoding_DefaultEncodingIsSetCorrectly() {
        FilterConfig filterConfigMock = mock(FilterConfig.class);
        when(filterConfigMock.getInitParameter("requestEncoding")).thenReturn(null);

        try {
            charsetFilter.init(filterConfigMock);

            assertEquals("UTF-8", charsetFilter.getEncoding());
        } catch (ServletException servletException) {
            fail();
        }
    }

    @Test
    public void doFilter_EncodingIsSetAndNoEncodingIsSetInRequest_SameEncodingIsSetInRequest() {
        String encoding = "UTF-8";
        ServletRequest requestMock = mock(ServletRequest.class);
        when(requestMock.getCharacterEncoding()).thenReturn(null);
        charsetFilter.setEncoding(encoding);

        try {
            charsetFilter.doFilter(requestMock, mock(ServletResponse.class), mock(FilterChain.class));

            verify(requestMock).setCharacterEncoding(encoding);
        } catch (IOException | ServletException exception) {
            fail();
        }
    }

    @Test
    public void doFilter_SomeResponse_ContentTypeIsSetInResponse() {
        ServletResponse responseMock = mock(ServletResponse.class);

        try {
            charsetFilter.doFilter(mock(ServletRequest.class), responseMock, mock(FilterChain.class));

            verify(responseMock).setContentType("text/html; charset=UTF-8");
        } catch (IOException | ServletException exception) {
            fail();
        }
    }

    @Test
    public void doFilter_SomeResponse_CharacterEncodingIsSetInResponse() {
        ServletResponse responseMock = mock(ServletResponse.class);

        try {
            charsetFilter.doFilter(mock(ServletRequest.class), responseMock, mock(FilterChain.class));

            verify(responseMock).setCharacterEncoding("UTF-8");
        } catch (IOException | ServletException exception) {
            fail();
        }
    }

    @Test
    public void doFilter_SomeArguments_NextInFilterChainIsTriggered() {
        ServletRequest requestMock = mock(ServletRequest.class);
        ServletResponse responseMock = mock(ServletResponse.class);
        FilterChain filterChainMock = mock(FilterChain.class);

        try {
            charsetFilter.doFilter(requestMock, responseMock, filterChainMock);

            verify(filterChainMock).doFilter(requestMock, responseMock);
        } catch (IOException | ServletException exception) {
            fail();
        }
    }

    @Test
    public void destroy() {
        charsetFilter.destroy();
    }
}