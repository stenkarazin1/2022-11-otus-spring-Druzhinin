package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BookUniqueData {
    private final String title;
    private final String author_name;
    private final int year_publication;
}
