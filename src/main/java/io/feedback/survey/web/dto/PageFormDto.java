package io.feedback.survey.web.dto;

import io.feedback.survey.entity.Page;
import io.feedback.survey.web.model.PageModel;
import org.springframework.validation.BindingResult;

public class PageFormDto {

    private PageModel pageModel;

    private BindingResult bindingResult;

    private Page page;

    public PageModel getPageModel() {
        return pageModel;
    }

    public void setPageModel(PageModel pageModel) {
        this.pageModel = pageModel;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}