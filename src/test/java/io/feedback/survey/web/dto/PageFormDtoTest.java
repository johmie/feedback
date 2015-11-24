package io.feedback.survey.web.dto;

import io.feedback.survey.entity.Page;
import io.feedback.survey.web.model.PageModel;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class PageFormDtoTest {

    private PageFormDto pageFormDto;

    @Before
    public void setUp() {
        pageFormDto = new PageFormDto();
    }

    @Test
    public void getAndSetPageModel() {
        PageModel pageModelMock = mock(PageModel.class);
        pageFormDto.setPageModel(pageModelMock);
        assertEquals(pageModelMock, pageFormDto.getPageModel());
    }

    @Test
    public void getAndSetBindingResult() {
        BindingResult bindingResultMock = mock(BindingResult.class);
        pageFormDto.setBindingResult(bindingResultMock);
        assertEquals(bindingResultMock, pageFormDto.getBindingResult());
    }

    @Test
    public void getAndSetPage() {
        Page pageMock = mock(Page.class);
        pageFormDto.setPage(pageMock);
        assertEquals(pageMock, pageFormDto.getPage());
    }
}