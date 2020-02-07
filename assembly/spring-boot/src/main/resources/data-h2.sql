DROP TABLE IF EXISTS CITIZEN;

CREATE TABLE CITIZEN (
  citizen_id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  nickname VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  country VARCHAR(50) NOT NULL,
  create_date date,
  update_date date,
);

INSERT INTO CITIZEN (name, surname, nickname, email, password, country) VALUES
  ('Angelo', 'Del Prete', 'nickname', 'email@email.com', 'password', 'country'),
  ('Laura', 'Palmer', 'nickname2', 'email2@email.com', 'password2', 'country2'),
  ('Patrick', 'swayze', 'dirty', 'dirty@dirty.com', 'dirty', 'USA'),
  ('Keanu', 'Reeves', 'TheOne', 'one@one.com', 'one', 'USA');