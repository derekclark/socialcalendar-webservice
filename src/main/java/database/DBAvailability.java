package database;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import representation.Availability;

import java.time.LocalDateTime;

@RegisterMapper(AvailabilityMapper.class)
public interface DBAvailability {
    @SqlUpdate("INSERT INTO AVAILABILITY (start_date, end_date, owner_name, owner_email, status, title) " +
            "VALUES (:start_date, :end_date, :availability.ownerName," +
            ":availability.ownerEmail, :availability.status, :availability.title)")
    int save(@BindBean("availability") Availability availability,
             @Bind(value = "start_date", binder = LocalDateTimeBinder.class) LocalDateTime startDate,
             @Bind(value = "end_date", binder = LocalDateTimeBinder.class) LocalDateTime endDate);

    @SqlQuery("SELECT * FROM AVAILABILITY WHERE id = :id")
    Availability findById(@Bind("id") int id);

    @SqlUpdate("DELETE FROM AVAILABILITY WHERE `id`= :id")
    int deleteById(@Bind("id") int id);


}
