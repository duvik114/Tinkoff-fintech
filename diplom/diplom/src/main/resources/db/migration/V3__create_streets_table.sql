CREATE TABLE streets
(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    description VARCHAR(255) NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longShot DOUBLE PRECISION NOT NULL,
    startTime TIME,
    closeTime TIME,
    price DOUBLE PRECISION,
    rating DOUBLE PRECISION,
    site VARCHAR(64),
    sightList VARCHAR(1024),
    length DOUBLE PRECISION
);