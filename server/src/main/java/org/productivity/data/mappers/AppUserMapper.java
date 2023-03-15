package org.productivity.data.mappers;

import org.productivity.models.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AppUserMapper implements RowMapper<AppUser> {
    private final List<String> roles;

    public AppUserMapper(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public AppUser mapRow(ResultSet rs, int i) throws SQLException {
        return new AppUser(
                rs.getInt("user_id"),
                rs.getString("username"),
                rs.getString("password_hash"),
                rs.getBoolean("enabled"),
                roles);
    }
}
