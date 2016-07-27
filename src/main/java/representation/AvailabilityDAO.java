package representation;

import database.DBAvailability;

public class AvailabilityDAO {
    private DBAvailability dbAvailability;

    public AvailabilityDAO(DBAvailability dbAvailability) {
        this.dbAvailability = dbAvailability;
    }

    public int save (Availability availability){
        return dbAvailability.createAvailability(availability);
    }

    public Availability read(int id) {
        return dbAvailability.findAvailabilityById(id);

    }

    public int deleteById(int id) {
        return dbAvailability.deleteAvailabilityById(id);
    }
}
