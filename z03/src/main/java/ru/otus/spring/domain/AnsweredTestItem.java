package ru.otus.spring.domain;

public class AnsweredTestItem {

    private final TestItem testItem;
    private final Integer userAnswer;       // Номер варианта ответа, введенный пользователем

    public AnsweredTestItem( TestItem testItem, Integer userAnswer ) {
        this.testItem = testItem;
        this.userAnswer = userAnswer;
    }

    public String getQuestion() {
        return this.testItem.getQuestion();
    }

    public TestItem getTestItem() {
        return this.testItem;
    }

    public int getUserAnswer() {
        return this.userAnswer;
    }

    public String getVariant( int i ) { return getTestItem().getVariant( i ).getVariantText(); }

}
