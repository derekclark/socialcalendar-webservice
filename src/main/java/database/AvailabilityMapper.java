package database;


import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import representation.availability.Availability;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AvailabilityMapper implements ResultSetMapper<Availability>{
    @Override
    public Availability map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        String title = r.getString("title");
        String ownerName = r.getString("owner_name");
        String ownerEmail = r.getString("owner_email");
        String status = r.getString("status");

        DateTime sDate = getDateTime(r, "start_date");
        DateTime eDate = getDateTime(r, "end_date");

        return new Availability(title, ownerEmail, ownerName, status, null, sDate, eDate);
    }

    private DateTime getDateTime(ResultSet r, String dateDescription) throws SQLException {
        Timestamp date = r.getTimestamp(dateDescription);
        return new DateTime(date.getTime());
    }
}
