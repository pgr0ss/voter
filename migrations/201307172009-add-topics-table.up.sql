CREATE TABLE topics (
  id serial PRIMARY KEY,
  text text NOT NULL,
  votes integer DEFAULT 1
);
