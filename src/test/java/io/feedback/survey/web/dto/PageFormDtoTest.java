package io.feedback.survey.web.dto;

import io.feedback.survey.entity.Page;
import io.feedback.survey.web.model.PageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class PageFormDtoTest {

    private PageFormDto pageFormDto;

    @BeforeEach
    public void setUp() {
        pageFormDto = new PageFormDto();
    }

    @Test
    public void setPageModel_SomePageModel_SameValueIsReturnedByGetPageModel() {
        PageModel pageModelMock = mock(PageModel.class);

        pageFormDto.setPageModel(pageModelMock);

        assertEquals(pageModelMock, pageFormDto.getPageModel());
    }

    @Test
    public void setBindingResult_SomeBindingResult_SameValueIsReturnedByGetBindingResult() {
        BindingResult bindingResultMock = mock(BindingResult.class);

        pageFormDto.setBindingResult(bindingResultMock);

        assertEquals(bindingResultMock, pageFormDto.getBindingResult());
    }

    @Test
    public void setPage_SomePage_SameValueIsReturnedByGetPage() {
        Page pageMock = mock(Page.class);

        pageFormDto.setPage(pageMock);

        assertEquals(pageMock, pageFormDto.getPage());
    }
}