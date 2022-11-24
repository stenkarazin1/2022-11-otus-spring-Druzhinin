package ru.otus.springboot.domain;

import java.util.List;

public class TestItem {

    private final String question;
    private final List< Variant > variantList;

    public TestItem ( String question, List< Variant > variantList ) {
        this.question = question;
        this.variantList = variantList;
    }

    public String getQuestion() {
        return question;
    }

    public Variant getVariant( Integer i ) { return variantList.get( i - 1 ); }

    public List< Variant > getVariantList() { return variantList; }

    public String getRightAnswer() {
        for ( Variant variant : variantList ) {
            if( variant.getIsRight() ) {
                return variant.getVariantText();
            }
        }
        return null;
    }

}
