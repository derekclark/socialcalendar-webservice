package database;


import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import representation.Availability;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AvailabilityMapper implements ResultSetMapper<Availability>{
    @Override
    public Availability map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        String title = r.getString("title");
        String ownerName = r.getString("owner_name");
        String ownerEmail = r.getString("owner_email");
        String status = r.getString("status");
        return new Availability(title, ownerEmail, ownerName, status);
    }
}
