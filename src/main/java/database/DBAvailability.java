package database;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import representation.availability.Availability;

import java.util.List;

@RegisterMapper(AvailabilityMapper.class)
public interface DBAvailability {
    @SqlUpdate("INSERT INTO AVAILABILITY (owner_name, owner_email, status, title, start_date, end_date) " +
            "VALUES (:availability.ownerName," +
            ":availability.ownerEmail, :availability.status, :availability.title, :availability.startDateTime," +
            ":availability.endDateTime)")
    @GetGeneratedKeys()
    int create(@BindBean("availability") Availability availability);

    @SqlQuery("SELECT * FROM AVAILABILITY WHERE id = :id")
    Availability findById(@Bind("id") int id);

    @SqlUpdate("DELETE FROM AVAILABILITY WHERE `id`= :id")
    int deleteById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM AVAILABILITY")
    List<Availability> findByUserId(@Bind("owner_email") String owner_email);
}
