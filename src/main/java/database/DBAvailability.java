package database;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import representation.availability.Availability;

@RegisterMapper(AvailabilityMapper.class)
public interface DBAvailability {
    @SqlUpdate("INSERT INTO AVAILABILITY (owner_name, owner_email, status, title, start_date) " +
            "VALUES (:availability.ownerName," +
            ":availability.ownerEmail, :availability.status, :availability.title, :availability.startDateTime)")
    @GetGeneratedKeys()
    int create(@BindBean("availability") Availability availability);

    @SqlQuery("SELECT * FROM AVAILABILITY WHERE id = :id")
    Availability findById(@Bind("id") int id);

    @SqlUpdate("DELETE FROM AVAILABILITY WHERE `id`= :id")
    int deleteById(@Bind("id") int id);


}
