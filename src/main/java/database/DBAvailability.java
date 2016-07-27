package database;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import representation.Availability;

@RegisterMapper(AvailabilityMapper.class)
public interface DBAvailability {
    @SqlUpdate("INSERT INTO AVAILABILITY (owner_name, owner_email, status, title) " +
            "VALUES (:availability.ownerName," +
            ":availability.ownerEmail, :availability.status, :availability.title)")
    int createAvailability(@BindBean("availability") Availability availability);

    @SqlQuery("SELECT * FROM AVAILABILITY WHERE id = :id")
    Availability findAvailabilityById(@Bind("id") int id);

    @SqlUpdate("DELETE FROM AVAILABILITY WHERE `id`= :id")
    int deleteAvailabilityById(@Bind("id") int id);


}
