package representation;

import database.DBAvailability;

public class AvailabilityDAO {
    private DBAvailability dbAvailability;

    public AvailabilityDAO(DBAvailability dbAvailability) {
        this.dbAvailability = dbAvailability;
    }

    public int save (Availability availability){
        return dbAvailability.save(availability,availability.getStartDate());
    }
}
