package io.feedback.survey.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class QuestionTest {

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question();
    }

    @Test
    void setType_SomeType_SameValueIsReturnedByGetType() {
        Question.Type type = Question.Type.SINGLE_CHOICE;

        question.setType(type);

        assertEquals(type, question.getType());
    }

    @Test
    void setAnswers_SomeAnswers_SameValueIsReturnedByGetAnswers() {
        Set<Answer> answerMocks = new HashSet<>();

        question.setAnswers(answerMocks);

        assertEquals(answerMocks, question.getAnswers());
    }

    @Test
    void setPage_SomePage_SameValueIsReturnedByGetPage() {
        Page pageMock = mock(Page.class);

        question.setPage(pageMock);

        assertEquals(pageMock, question.getPage());
    }

    @Test
    void setName_SomeName_SameValueIsReturnedByGetName() {
        String name = "Name";

        question.setName(name);

        assertEquals(name, question.getName());
    }

    @Test
    void setTitle_SomeTitle_SameValueIsReturnedByGetTitle() {
        String title = "Title";

        question.setTitle(title);

        assertEquals(title, question.getTitle());
    }

    @Test
    void setPosition_SomePosition_SameValueIsReturnedByGetPosition() {
        int position = 1;

        question.setPosition(position);

        assertEquals(position, question.getPosition());
    }

    @Test
    void setAnswerRows_SomeAnswerRows_SameValueIsReturnedByGetAnswerRows() {
        Set<AnswerRow> answerRowMocks = new HashSet<>();

        question.setAnswerRows(answerRowMocks);

        assertEquals(answerRowMocks, question.getAnswerRows());
    }

    @Test
    void isMatrix_TypeIsMatrixSingleChoice_ReturnsTrue() {
        question.setType(Question.Type.MATRIX_SINGLE_CHOICE);

        assertTrue(question.isMatrix());
    }

    @Test
    void isMatrix_TypeIsMatrixMultipleChoice_ReturnsTrue() {
        question.setType(Question.Type.MATRIX_MULTIPLE_CHOICE);

        assertTrue(question.isMatrix());
    }

    @Test
    void isMatrix_TypeIsSingleChoice_ReturnsFalse() {
        question.setType(Question.Type.SINGLE_CHOICE);

        assertFalse(question.isMatrix());
    }

    @Test
    void isMatrix_TypeIsNull_ReturnsFalse() {
        question.setType(null);

        assertFalse(question.isMatrix());
    }

    @Test
    void setId_SomeId_SameValueIsReturnedByGetId() {
        Long id = 1L;

        question.setId(id);

        assertEquals(id, question.getId());
    }

    @Test
    void setVersion_SomeVersion_SameValueIsReturnedByGetVersion() {
        Long version = 1L;

        question.setVersion(version);

        assertEquals(version, question.getVersion());
    }
}