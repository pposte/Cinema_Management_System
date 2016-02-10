CREATE TABLE person_tab (
  person_ID VARCHAR (60) NOT NULL,
  name VARCHAR(150),
  birth DATE,
  gender CHAR(1),
  PRIMARY KEY ( person_ID )
);

CREATE TABLE movie_tab (
  movie_ID VARCHAR (60) NOT NULL,
  title VARCHAR(150),
  genre VARCHAR(45),
  year INT,
  length INT,
  PG_rated VARCHAR(45),
  PRIMARY KEY (movie_ID)
);

CREATE TABLE actor_tab (
  actor_ID VARCHAR (60) NOT NULL,
  person_ID VARCHAR (60) NOT NULL,
  movie_ID VARCHAR (60) NOT NULL,
  role VARCHAR(150),
  PRIMARY KEY (actor_ID),
  CONSTRAINT person_tab_actor_fk FOREIGN KEY (person_ID) REFERENCES person_tab(person_ID),
  CONSTRAINT movie_tab_actor_fk FOREIGN KEY (movie_ID) REFERENCES movie_tab(movie_ID)
);

CREATE TABLE writer_tab (
  person_ID VARCHAR (60) NOT NULL,
  movie_ID VARCHAR (60) NOT NULL,
  PRIMARY KEY (person_ID, movie_ID),
  CONSTRAINT person_tab_writer_fk FOREIGN KEY (person_ID) REFERENCES person_tab(person_ID),
  CONSTRAINT movie_tab_writer_fk FOREIGN KEY (movie_ID) REFERENCES movie_tab(movie_ID)
);

CREATE TABLE director_tab (
  person_ID VARCHAR (60) NOT NULL,
  movie_ID VARCHAR (60) NOT NULL,
  PRIMARY KEY (person_ID, movie_ID),
  CONSTRAINT person_tab_director_fk FOREIGN KEY (person_ID) REFERENCES person_tab(person_ID),
  CONSTRAINT movie_tab_director_fk FOREIGN KEY (movie_ID) REFERENCES movie_tab(movie_ID)
);


CREATE TABLE member_tab (
  member_ID VARCHAR (60) NOT NULL,
  name VARCHAR(150),
  PRIMARY KEY ( member_ID )
);

CREATE TABLE payment_tab (
  method VARCHAR(45) NOT NULL,
  member_ID VARCHAR (60) NOT NULL,
  PRIMARY KEY (method, member_ID),
  CONSTRAINT member_tab_payment_fk FOREIGN KEY (member_ID) REFERENCES member_tab(member_ID)
);

CREATE TABLE staff_tab (
  staff_ID VARCHAR (60) NOT NULL,
  name VARCHAR(150),
  PRIMARY KEY (staff_ID)
);

CREATE TABLE room_tab (
  room_ID VARCHAR (60) NOT NULL,
  capacity INT,
  room_number INT,
  PRIMARY KEY (room_ID)
);

CREATE TABLE showing_tab (
  showing_ID VARCHAR (60) NOT NULL,
  movie_ID VARCHAR (60) NOT NULL,
  room_ID VARCHAR (60) NOT NULL,
  time VARCHAR (20),
  price NUMERIC(4,2),
  PRIMARY KEY (showing_ID),
  CONSTRAINT movie_tab_showing_fk FOREIGN KEY (movie_ID) REFERENCES movie_tab(movie_ID),
  CONSTRAINT room_tab_showing_fk FOREIGN KEY (room_ID) REFERENCES room_tab(room_ID)
);

CREATE TABLE ticket_tab (
    ticket_ID VARCHAR (60) NOT NULL,
    method VARCHAR(45) NOT NULL,
    member_ID VARCHAR (60) NOT NULL,
    showing_ID VARCHAR (60) NOT NULL,
    PRIMARY KEY (ticket_ID),
    CONSTRAINT payment_tab_member_fk FOREIGN KEY (method, member_ID) REFERENCES payment_tab(method, member_ID),
    CONSTRAINT showing_tab_movie_fk FOREIGN KEY (showing_ID) REFERENCES showing_tab(showing_ID)
);

CREATE TABLE movie_rating_tab (
  rating FLOAT NULL,
  movie_ID VARCHAR (60) NOT NULL,
  member_ID VARCHAR (60) NOT NULL,
  PRIMARY KEY (movie_ID, member_ID),
  CONSTRAINT movie_rating_tab_member_fk FOREIGN KEY (member_ID) REFERENCES member_tab(member_ID),
  CONSTRAINT movie_rating_tab_movie_fk FOREIGN KEY (movie_ID) REFERENCES movie_tab(movie_ID)
);

CREATE TABLE actor_rating_tab (
  rating FLOAT NULL,
  person_ID VARCHAR (60) NOT NULL,
  member_ID VARCHAR (60) NOT NULL,
  PRIMARY KEY (person_ID, member_ID),
  CONSTRAINT actor_rating_tab_member_fk FOREIGN KEY (member_ID) REFERENCES member_tab(member_ID),
  CONSTRAINT actor_rating_tab_person_fk FOREIGN KEY (person_ID) REFERENCES person_tab(person_ID)
);
