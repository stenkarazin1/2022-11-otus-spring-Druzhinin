INSERT INTO genre (genre_name) VALUES ('Конформизм'), ('Постмодернизм'), ('Нонконформизм'), ('Сюрреализм'), ('Соцреализм');

INSERT INTO author (author_name) VALUES ('Гоголь Н.В.'), ('Бруно Д.'), ('Достоевский Ф.М.'), ('Булгаков М.А.'), ('Тургенев И.С.');

INSERT INTO book (title, genre_id, author_id, year_publication, available_quantity) VALUES
('Вий', (SELECT genre_id FROM genre WHERE genre_name = 'Сюрреализм'), (SELECT author_id FROM author WHERE author_name = 'Гоголь Н.В.'), 2019, 666),
('Мастер и Маргарита', (SELECT genre_id FROM genre WHERE genre_name = 'Сюрреализм'), (SELECT author_id FROM author WHERE author_name = 'Булгаков М.А.'), 2001, 21),
('Отцы и дети', (SELECT genre_id FROM genre WHERE genre_name = 'Нонконформизм'), (SELECT author_id FROM author WHERE author_name = 'Тургенев И.С.'), 2001, 3),
('Преступление и наказание', (SELECT genre_id FROM genre WHERE genre_name = 'Постмодернизм'), (SELECT author_id FROM author WHERE author_name = 'Достоевский Ф.М.'), 2010, 7),
('Му-му', (SELECT genre_id FROM genre WHERE genre_name = 'Конформизм'), (SELECT author_id FROM author WHERE author_name = 'Тургенев И.С.'), 2011, 3),
('О бесконечности, вселенной и мирах', (SELECT genre_id FROM genre WHERE genre_name = 'Нонконформизм'), (SELECT author_id FROM author WHERE author_name = 'Бруно Д.'), 2010, 8),
('Нос', (SELECT genre_id FROM genre WHERE genre_name = 'Сюрреализм'), (SELECT author_id FROM author WHERE author_name = 'Гоголь Н.В.'), 2008, 1);