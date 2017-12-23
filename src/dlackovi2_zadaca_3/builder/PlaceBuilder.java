package dlackovi2_zadaca_3.builder;

import dlackovi2_zadaca_3.model.Device;
import dlackovi2_zadaca_3.model.Place;
import java.util.List;

/**
 *
 * @author dlackovi2
 */
public interface PlaceBuilder
{
    Place build();
    PlaceBuilder setId(final int id);
    PlaceBuilder setName(final String name);
    PlaceBuilder setType(final int type);
    PlaceBuilder setnSensors(final int nSensors);
    PlaceBuilder setnActuators(final int nActuators);
    PlaceBuilder setUsable(final boolean usable);
    PlaceBuilder setDevices(final List<Device> devices);
}
