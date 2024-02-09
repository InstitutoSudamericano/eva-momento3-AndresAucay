
DROP VIEW IF EXISTS film_view;


CREATE VIEW film_view AS
SELECT
    f.id AS film_id,
    f.title AS film_title,
    f.director AS film_director,
    s.id AS scene_id,
    s.description AS scene_description
FROM
    film f
        JOIN
    scene s ON f.id = s.film_id;
