package dlackovi2_zadaca_3.model;

/**
 *
 * @author dlackovi2
 */
public class Sensor extends DeviceRandomStatus implements Cloneable
{
    private int id;
    private String name;
    private int type;
    private int kind;
    private float minValue;
    private float maxValue;
    private String comment;
    private float value;
    private boolean ascend = true;
    
    public Sensor()
    {
        
    }

    public Sensor(int id, String name, int type, int kind, float minValue, float maxValue, String comment)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.kind = kind;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.comment = comment;
        value = minValue;
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
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

    public int getKind()
    {
        return kind;
    }

    public void setKind(int kind)
    {
        this.kind = kind;
    }

    public float getMinValue()
    {
        return minValue;
    }

    public void setMinValue(float minValue)
    {
        this.minValue = minValue;
    }

    public float getMaxValue()
    {
        return maxValue;
    }

    public void setMaxValue(float maxValue)
    {
        this.maxValue = maxValue;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public boolean isAscend() {
        return ascend;
    }

    public void setAscend(boolean ascend) {
        this.ascend = ascend;
    }

    
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
