ALTER TABLE mycity.city.buildings
ADD if not exists material varchar;

CREATE TABLE IF NOT EXISTS mycity.city.flats(
    id serial PRIMARY KEY,
    number int,
    square int,
    rooms_number int,
    building bigint
);
