CREATE TABLE IF NOT EXISTS film(
    id SERIAL PRIMARY KEY,
    title VARCHAR(100),
    director VARCHAR(100),
    duration INT,
    investment DECIMAL
    );

CREATE TABLE IF NOT EXISTS scene(
    id SERIAL PRIMARY KEY,
    description VARCHAR(100),
    budget DECIMAL(10, 2),
    minutes INT,
    film_id INT NOT NULL,
    FOREIGN KEY (film_id) REFERENCES film(id)
    );

CREATE TABLE IF NOT EXISTS characters(
    id SERIAL PRIMARY KEY,
    name_personaje VARCHAR(100),
    description VARCHAR(100),
    cost DECIMAL(10, 2),
    gender VARCHAR(50),
    scene_id INT NOT NULL,
    FOREIGN KEY (scene_id) REFERENCES scene(id)
    );
