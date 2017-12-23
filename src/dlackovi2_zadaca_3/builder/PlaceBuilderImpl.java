package dlackovi2_zadaca_3.builder;

import dlackovi2_zadaca_3.model.Device;
import dlackovi2_zadaca_3.model.Place;
import java.util.List;

/**
 *
 * @author dlackovi2
 */
public class PlaceBuilderImpl implements PlaceBuilder
{
    private Place place;
    
    public PlaceBuilderImpl()
    {
        this.place = new Place();
    }
    
    @Override
    public Place build()
    {
        return this.place;
    }

    @Override
    public PlaceBuilder setId(int id)
    {
        this.place.setId(id);
        return this;
    }

    @Override
    public PlaceBuilder setName(String name)
    {
        this.place.setName(name);
        return this;
    }

    @Override
    public PlaceBuilder setType(int type)
    {
        this.place.setType(type);
        return this;
    }

    @Override
    public PlaceBuilder setnSensors(int nSensors)
    {
        this.place.setnSensors(nSensors);;
        return this;
    }

    @Override
    public PlaceBuilder setnActuators(int nActuators)
    {
        this.place.setnActuators(nActuators);
        return this;
    }

    @Override
    public PlaceBuilder setUsable(boolean usable)
    {
        this.place.setUsable(usable);
        return this;
    }

    @Override
    public PlaceBuilder setDevices(List<Device> devices)
    {
        this.place.setDevices(devices);
        return this;
    }
}
