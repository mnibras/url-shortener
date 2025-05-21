INSERT INTO users (email, password, name, role)
VALUES ('admin@gmail.com', 'admin', 'Administrator', 'ROLE_ADMIN'),
       ('siva@gmail.com', 'secret', 'Siva', 'ROLE_USER');

INSERT INTO short_urls (short_key, original_url, created_by, created_at, expires_at, is_private, click_count)
VALUES ('rs1Aed', 'https://www.sivalabs.in/code-offline-with-local-ai-ollama', 1, TIMESTAMP '2024-07-15', NULL, FALSE,0),
       ('hujfDf', 'https://www.sivalabs.in/run-spring-boot-testcontainers-tests-at-jet-speed', 1,TIMESTAMP '2024-07-16', NULL, FALSE, 0),
       ('ertcbn', 'https://www.sivalabs.in/running-custom-spring-initializr', 1, TIMESTAMP '2024-07-17', NULL, FALSE,0),
       ('edfrtg', 'https://www.sivalabs.in/an-email-template-of-asking-for-technical-help', 1, TIMESTAMP '2024-07-18',NULL, TRUE, 0),
       ('vbgtyh', 'https://www.sivalabs.in/mastering-spring-boot-in-5-stages', 1, TIMESTAMP '2024-07-19', NULL, FALSE,0);