INSERT INTO tb_franchise(franchise_description, franchise_name)
VALUES ('A trilogy made by JR tolkien','The Lord of the Rings');

INSERT INTO tb_franchise(franchise_description, franchise_name)
VALUES ('A trilogy made by George Lucas','Star Wars');

INSERT INTO tb_franchise(franchise_description, franchise_name)
VALUES ('A trilogy made by Christopher Nolan','The Dark Knight');

INSERT INTO tb_movie(movie_director, movie_genre, movie_trailer_url, movie_picture_url, movie_release_year, movie_title, franchise_id)
VALUES ('Peter Jackson','Fantasy','https://www.youtube.com/watch?v=V75dMMIW2B4','https://http.cat/100','2001','The Fellowship of the Ring',1);

INSERT INTO tb_movie(movie_director, movie_genre, movie_trailer_url, movie_picture_url, movie_release_year, movie_title, franchise_id)
VALUES ('Peter Jackson','Fantasy','https://www.youtube.com/watch?v=V75dMMIW2B4','https://http.cat/100','2002','The Two Towers',1);

INSERT INTO tb_movie(movie_director, movie_genre, movie_trailer_url, movie_picture_url, movie_release_year, movie_title, franchise_id)
VALUES ('Peter Jackson','Fantasy','https://www.youtube.com/watch?v=V75dMMIW2B4','https://http.cat/100','2003','The Return of the King',1);

INSERT INTO tb_movie(movie_director, movie_genre, movie_trailer_url, movie_picture_url, movie_release_year, movie_title, franchise_id)
VALUES ('George Lucas','Fantasy','https://www.youtube.com/watch?v=V75dMMIW2B4','https://http.cat/100','1977','Star Wars',2);

INSERT INTO tb_movie(movie_director, movie_genre, movie_trailer_url, movie_picture_url, movie_release_year, movie_title, franchise_id)
VALUES ('George Lucas','Fantasy','https://www.youtube.com/watch?v=V75dMMIW2B4','https://http.cat/100','1980','The Empire Strikes Back',2);

INSERT INTO tb_movie(movie_director, movie_genre, movie_trailer_url, movie_picture_url, movie_release_year, movie_title, franchise_id)
VALUES ('George Lucas','Fantasy','https://www.youtube.com/watch?v=V75dMMIW2B4','https://http.cat/100','1983','Return of the Jedi',2);

INSERT INTO tb_movie(movie_director, movie_genre, movie_trailer_url, movie_picture_url, movie_release_year, movie_title, franchise_id)
VALUES ('Christopher Nolan','Fantasy','https://www.youtube.com/watch?v=V75dMMIW2B4','https://http.cat/100','2005','Batman Begins',3);

INSERT INTO tb_movie(movie_director, movie_genre, movie_trailer_url, movie_picture_url, movie_release_year, movie_title, franchise_id)
VALUES ('Christopher Nolan','Fantasy','https://www.youtube.com/watch?v=V75dMMIW2B4','https://http.cat/100','2008','The Dark Knight',3);

INSERT INTO tb_movie(movie_director, movie_genre, movie_trailer_url, movie_picture_url, movie_release_year, movie_title, franchise_id)
VALUES ('Christopher Nolan','Fantasy','https://www.youtube.com/watch?v=V75dMMIW2B4','https://http.cat/100','2012','The Dark Knight Rises',3);

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Sam', 'Male', 'Samwise Gamgee', 'https://http.cat/100');

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Frodo', 'Male', 'Frodo Baggins', 'https://http.cat/100');

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Aragorn', 'Male', 'Aragorn', 'https://http.cat/100');

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Legolas', 'Male', 'Legolas', 'https://http.cat/100');

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Gimli', 'Male', 'Gimli', 'https://http.cat/100');

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Boromir', 'Male', 'Boromir', 'https://http.cat/100');

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Han Solo', 'Male', 'Han Solo', 'https://http.cat/100');

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Luke Skywalker', 'Male', 'Luke Skywalker', 'https://http.cat/100');

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Princess Leia', 'Female', 'Leia Organa', 'https://http.cat/100');

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Darth Vader', 'Male', 'Anakin Skywalker / Darth Vader', 'https://http.cat/100');

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Batman', 'Male', 'Bruce Wayne / Batman', 'https://http.cat/100');

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Joker', 'Male', 'The Joker', 'https://http.cat/100');

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Alfred', 'Male', 'Alfred Pennyworth', 'https://http.cat/100');

INSERT INTO tb_franchise(franchise_description, franchise_name)
VALUES ('A trilogy made by Francis Ford Coppola','The Godfather');

INSERT INTO tb_movie(movie_director, movie_genre, movie_trailer_url, movie_picture_url, movie_release_year, movie_title, franchise_id)
VALUES ('Francis Ford Coppola','Crime','https://www.youtube.com/watch?v=sY1S34973zA','https://http.cat/100','1972','The Godfather',4);

INSERT INTO tb_movie(movie_director, movie_genre, movie_trailer_url, movie_picture_url, movie_release_year, movie_title, franchise_id)
VALUES ('Francis Ford Coppola','Crime','https://www.youtube.com/watch?v=7oNZ4LVjYGY','https://http.cat/100','1974','The Godfather Part II',4);

INSERT INTO tb_movie(movie_director, movie_genre, movie_trailer_url, movie_picture_url, movie_release_year, movie_title, franchise_id)
VALUES ('Francis Ford Coppola','Crime','https://www.youtube.com/watch?v=vvC3uZ7D0a4','https://http.cat/100','1990','The Godfather Part III',4);

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Vito Corleone', 'Male', 'Marlon Brando', 'https://http.cat/100');

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Michael Corleone', 'Male', 'Al Pacino', 'https://http.cat/100');

INSERT INTO tb_character(character_alias, character_gender, character_name, character_picture_url)
VALUES ('Kay Adams-Corleone', 'Female', 'Diane Keaton', 'https://http.cat/100');

-- adding the lord of the rings movies
INSERT INTO tb_movie_characters(movies_movie_id, characters_character_id)
VALUES
    (1,1),
    (2,1),
    (3,1),
    (1,2),
    (2,2),
    (3,2),
    (1,3),
    (2,3),
    (3,3),
    (1,4),
    (2,4),
    (3,4),
    (1,5),
    (2,5),
    (3,5),
    (1,6),
    (2,6),
    (3,6);

-- adding the star wars movies
INSERT INTO tb_movie_characters(movies_movie_id, characters_character_id)
VALUES
    (4,7),
    (5,7),
    (6,7),
    (4,8),
    (5,8),
    (6,8),
    (4,9),
    (5,9),
    (6,9);

-- adding the batman movies
INSERT INTO tb_movie_characters(movies_movie_id, characters_character_id)
VALUES
    (7,10),
    (8,10),
    (9,10),
    (7,11),
    (8,11),
    (9,11),
    (7,12),
    (8,12),
    (9,12),
    (7,13),
    (8,13),
    (9,13);
-- adding the godfather movies
INSERT INTO tb_movie_characters(movies_movie_id, characters_character_id)
VALUES
    (10,14),
    (11,14),
    (12,14),
    (10,15),
    (11,15),
    (12,15),
    (10,16),
    (11,16),
    (12,16);

