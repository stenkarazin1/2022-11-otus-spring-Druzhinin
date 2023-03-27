package ru.otus.spring.domain;

public class Variant {

    private final String variantText;
    private final Boolean isRight;

    public Variant( String variantText, Boolean isRight ) {
        this.variantText = variantText;
        this.isRight = isRight;
    }

    public String getVariantText() {
        return variantText;
    }

    public Boolean getIsRight() {
        return isRight;
    }

}

