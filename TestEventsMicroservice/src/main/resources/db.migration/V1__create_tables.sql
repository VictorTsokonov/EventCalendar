CREATE TABLE events (
                        id VARCHAR(50) PRIMARY KEY,
                        name VARCHAR(255),
                        country VARCHAR(100),
                        type VARCHAR(100),
                        description TEXT,
                        status_of_importance VARCHAR(100),
                        time TIMESTAMP,
                        actual DOUBLE,
                        forecast DOUBLE,
                        previous DOUBLE,
                        INDEX (time),
                        INDEX (time, country),
                        INDEX (time, type),
                        INDEX (time, status_of_importance),

                        INDEX (time, country, type),
                        INDEX (time, type, country),

                        INDEX (time, status_of_importance, country),
                        INDEX (time, country, status_of_importance)



);
CREATE TABLE linked_events (
                               event_id VARCHAR(50),
                               linked_event_id VARCHAR(50),
                               PRIMARY KEY (event_id, linked_event_id),
                               FOREIGN KEY (event_id) REFERENCES events(id),
                               FOREIGN KEY (linked_event_id) REFERENCES events(id)
);
