package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
// Класс Book не унаследован от BookUniqueData, т.к. в этом нет практической необходимости
public class Book {
    private final String title;
    private final int year_publication;
    private final int available_quantity;
    private final String author_name;
    private final String genre_name;
}
