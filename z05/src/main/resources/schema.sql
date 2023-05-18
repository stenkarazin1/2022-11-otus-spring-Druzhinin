DROP TABLE IF EXISTS genre;

CREATE TABLE genre (
    genre_id SERIAL PRIMARY KEY,
    -- cipher_UDK VARCHAR(10),  -- например, 519.6 (Вычислительная математика. Численный анализ)
    genre_name VARCHAR(50) UNIQUE
);

DROP TABLE IF EXISTS author;

CREATE TABLE author (
    author_id SERIAL PRIMARY KEY,
    -- cipher_alf VARCHAR(10),  -- например, Л-123
    author_name VARCHAR(50) UNIQUE
);

DROP TABLE IF EXISTS book;

CREATE TABLE book (
    book_id SERIAL PRIMARY KEY,
    title VARCHAR(150),
    genre_id SMALLINT NOT NULL,
    author_id SMALLINT NOT NULL,
    year_publication SMALLINT NOT NULL,
    available_quantity SMALLINT,
    FOREIGN KEY (genre_id) REFERENCES genre (genre_id),
    FOREIGN KEY (author_id) REFERENCES author (author_id) ON DELETE CASCADE,
    UNIQUE (title, author_id, year_publication)
);

CREATE INDEX idx_genre_id ON book USING BTREE(genre_id);

CREATE INDEX idx_author_id ON book USING BTREE(author_id);
