package database;


import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import representation.Availability;
import representation.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

public class AvailabilityMapper implements ResultSetMapper<Availability>{
    @Override
    public Availability map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        String title = r.getString("title");
        String ownerName = r.getString("owner_name");
        String ownerEmail = r.getString("owner_email");
        String status = r.getString("status");
        LocalDateTime startDate = getLocalDateTimeFrom(r, "start_date");
        LocalDateTime endDate = getLocalDateTimeFrom(r, "end_date");

        Set<User> sharedUsers = new HashSet<>();
        return new Availability(title, startDate, endDate, ownerEmail, ownerName, status, sharedUsers);
    }

    private LocalDateTime getLocalDateTimeFrom(ResultSet resultSet, String columnName) throws SQLException {
        return LocalDateTime.ofInstant(resultSet.getTimestamp(columnName).toInstant(), ZoneId.systemDefault());
    }


}
