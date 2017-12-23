package dlackovi2_zadaca_3.Visitor;

import dlackovi2_zadaca_3.model.Actuator;
import dlackovi2_zadaca_3.model.Place;
import dlackovi2_zadaca_3.model.Sensor;

/**
 *
 * @author dlackovi2
 */
public interface Visitor
{
    public void visit(Place place);
    public void visit(Sensor sensor);
    public void visit(Actuator actuator);
}
