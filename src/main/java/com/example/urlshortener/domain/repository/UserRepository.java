package com.example.urlshortener.domain.repository;

import com.example.urlshortener.domain.entity.User;
import com.example.urlshortener.domain.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

@Repository
public class UserRepository {

    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT id, email, password, name, role, created_at FROM users WHERE email = :email";
        return jdbcClient
                .sql(sql)
                .param("email", email)
                //.query(User.class)
                .query(new UserRowMapper())
                .optional();
    }

    public boolean existsByEmail(String email) {
        String sql = "SELECT count(*) > 0 FROM users WHERE email = :email";
        return jdbcClient
                .sql(sql)
                .param("email", email)
                .query(Boolean.class)
                .single();
    }

    public Optional<User> findById(Long id) {
        String sql = "SELECT id, email, password, name, role, created_at FROM users WHERE id = :id";
        return jdbcClient
                .sql(sql)
                .param("id", id)
                .query(new UserRowMapper())
                .optional();
    }

    public void save(User user) {
        String sql = """
                INSERT INTO users (email, password, name, role, created_at)
                VALUES (:email, :password, :name, :role, :createdAt)
                RETURNING id
                """;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(sql)
                .param("email", user.getEmail())
                .param("password", user.getPassword())
                .param("name", user.getName())
                .param("role", user.getRole().name())
                .param("createdAt", Timestamp.from(user.getCreatedAt()))
                .update(keyHolder);
        Long userId = keyHolder.getKeyAs(Long.class);
        log.info("User saved with Id: {}", userId);
    }

    static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            var user = new User();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setRole(Role.valueOf(rs.getString("role")));
            user.setCreatedAt(rs.getTimestamp("created_at").toInstant());
            return user;
        }

    }
}
