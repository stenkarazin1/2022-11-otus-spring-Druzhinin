INSERT INTO book (title, genre_id, author_id, year_publication, available_quantity) VALUES
('Ревизор', 2, 2, 2019, 666);

COMMIT;
--('Ревизор', (SELECT genre_id FROM genre WHERE genre_name = 'Конформизм'), (SELECT author_id FROM author WHERE author_name = 'Гоголь Н.В.'), 2019, 666);