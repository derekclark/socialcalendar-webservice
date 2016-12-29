package representation.availability;

import database.DBAvailability;

import javax.ws.rs.core.Response;

public class AvailabilityDAO {
    private DBAvailability dbAvailability;

    public AvailabilityDAO(DBAvailability dbAvailability) {
        this.dbAvailability = dbAvailability;
    }

    public int save (Availability availability){
        return dbAvailability.create(availability);
    }

    public Availability read(int id) {
        return dbAvailability.findById(id);
    }

    public int deleteById(int id) {
        return dbAvailability.deleteById(id);
    }

    public Response getMyAvailabilities(String userId) {
        return null;
    }
}
