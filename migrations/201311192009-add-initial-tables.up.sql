CREATE TABLE categories (
  id serial PRIMARY KEY,
  name text NOT NULL
);

CREATE TABLE topics (
  id serial PRIMARY KEY,
  category_id integer NOT NULL REFERENCES categories (id),
  text text NOT NULL,
  votes integer DEFAULT 1
);
