INSERT INTO users (email, password, name, role)
VALUES
    ('admin@gmail.com', '$2a$10$.bre7Qrdmd8lCOuO6Ua0wu010R8L/vrlpaEIz6DVQugtJOvRmlFfm', 'Administrator', 'ROLE_ADMIN'),
    ('nibras@gmail.com', '$2a$10$6f65jR00dxLRdFWRYnmQTOUKB1HxsS11DMvvmBrV1MtudevINDUfm', 'User', 'ROLE_USER');

INSERT INTO short_urls (
    short_key, original_url, created_by, created_at,
    expires_at, is_private, click_count
)
VALUES
    ('rs1Aed', 'https://spring.io/projects/spring-boot', 1, TIMESTAMP '2024-07-15', NULL, FALSE, 0),
    ('hujfDf', 'https://spring.io/projects/spring-data-jdbc', 1, TIMESTAMP '2024-07-16', NULL, FALSE, 0),
    ('ertcbn', 'https://spring.io/projects/spring-data-jpa', 1, TIMESTAMP '2024-07-17', NULL, FALSE, 0),
    ('edfrtg', 'https://spring.io/projects/spring-security', 1, TIMESTAMP '2024-07-18', NULL, TRUE, 0),
    ('vbgtyh', 'https://spring.io/projects/spring-session', 1, TIMESTAMP '2024-07-19', NULL, FALSE, 0),
    ('rtyfgb', 'https://spring.io/projects/spring-cloud', 1, TIMESTAMP '2024-07-25', NULL, FALSE, 0),
    ('rtvbop', 'https://spring.io/projects/spring-cloud-gateway', 1, TIMESTAMP '2024-07-26', NULL, FALSE, 0),
    ('2wedfg', 'https://spring.io/projects/spring-retry', 1, TIMESTAMP '2024-07-27', NULL, TRUE, 0),
    ('6yfrd4', 'https://spring.io/projects/spring-amqp', 1, TIMESTAMP '2024-07-28', NULL, FALSE, 0),
    ('r5t4tt', 'https://spring.io/projects/spring-batch', 1, TIMESTAMP '2024-07-29', NULL, FALSE, 0),
    ('ffr4rt', 'https://spring.io/projects/spring-kafka', 1, TIMESTAMP '2024-08-10', NULL, FALSE, 0),
    ('9oui7u', 'https://spring.io/projects/spring-integration', 1, TIMESTAMP '2024-08-11', NULL, FALSE, 0),
    ('cvbg5t', 'https://spring.io/projects/spring-hateoas', 1, TIMESTAMP '2024-08-12', NULL, FALSE, 0),
    ('nm6ytf', 'https://spring.io/projects/spring-data-rest', 1, TIMESTAMP '2024-08-13', NULL, FALSE, 0),
    ('tt5y6r', 'https://spring.io/projects/spring-graphql', 1, TIMESTAMP '2024-08-14', NULL, FALSE, 0),
    ('fgghty', 'https://spring.io/projects/spring-web-services', 1, TIMESTAMP '2024-08-15', NULL, FALSE, 0),
    ('f45tre', 'https://spring.io/projects/spring-mobile', 1, TIMESTAMP '2024-08-16', NULL, FALSE, 0),
    ('54rt54', 'https://spring.io/projects/spring-loaded', 1, TIMESTAMP '2024-08-17', NULL, FALSE, 0);
