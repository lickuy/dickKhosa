CREATE TABLE investor (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR(255),
   address VARCHAR(255),
   contact VARCHAR(255),
   email VARCHAR(255),
   age INT NOT NULL,
   archive BOOLEAN NOT NULL,
   CONSTRAINT pk_investor PRIMARY KEY (id)
);