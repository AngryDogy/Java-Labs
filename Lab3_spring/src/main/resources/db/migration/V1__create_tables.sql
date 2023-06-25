
CREATE SCHEMA IF NOT EXISTS mycity.city;
CREATE TABLE IF NOT EXISTS mycity.city.streets(
    id serial PRIMARY KEY,
    name varchar,
    postcode int);
CREATE TABLE IF NOT EXISTS mycity.city.buildings(
    id serial PRIMARY KEY,
    name varchar,
    date_of_construction date,
    number_of_floors int,
    building_type varchar,
    street bigint
);

