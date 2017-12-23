package dlackovi2_zadaca_3.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dlackovi2
 */
public class Place implements Cloneable
{
    private int id;
    private String name;
    private int type;
    private int nSensors;
    private int nActuators;
    private boolean usable;
    private List<Device> devices = new ArrayList<Device>();
    
    public Place()
    {
        
    }

    public Place(String naziv, int tip, int brojSenzora, int brojAktuatora, boolean koristi)
    {
        this.name = naziv;
        this.type = tip;
        this.nSensors = brojSenzora;
        this.nActuators = brojAktuatora;
        this.usable = koristi;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getnSensors()
    {
        return nSensors;
    }

    public void setnSensors(int nSensors)
    {
        this.nSensors = nSensors;
    }

    public int getnActuators()
    {
        return nActuators;
    }

    public void setnActuators(int nActuators)
    {
        this.nActuators = nActuators;
    }

    public boolean isUsable()
    {
        return usable;
    }

    public void setUsable(boolean usable)
    {
        this.usable = usable;
    }

    public List<Device> getDevices()
    {
        return devices;
    }

    public void setDevices(List<Device> devices)
    {
        this.devices = devices;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
