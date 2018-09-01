--DROP TABLE events;

CREATE TABLE IF NOT EXISTS events (
    id VARCHAR(2048),
    timestamp TIMESTAMP(9),
    source VARCHAR(12040)
);