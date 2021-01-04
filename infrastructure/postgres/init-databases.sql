CREATE ROLE uber WITH LOGIN PASSWORD 'uber';

CREATE ROLE visited WITH LOGIN PASSWORD 'visited' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
CREATE DATABASE visited_places_database;
GRANT ALL PRIVILEGES ON DATABASE visited_places_database TO visited;
GRANT ALL PRIVILEGES ON DATABASE visited_places_database TO uber;

CREATE ROLE keycloak WITH LOGIN PASSWORD 'keycloak' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
CREATE DATABASE keycloak_database;
GRANT ALL PRIVILEGES ON DATABASE keycloak_database TO keycloak;
GRANT ALL PRIVILEGES ON DATABASE keycloak_database TO uber;