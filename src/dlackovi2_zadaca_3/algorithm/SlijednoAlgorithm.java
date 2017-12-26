package dlackovi2_zadaca_3.algorithm;

import dlackovi2_zadaca_3.iterator.PlaceIterator;
import dlackovi2_zadaca_3.model.Actuator;
import dlackovi2_zadaca_3.model.Device;
import dlackovi2_zadaca_3.model.Place;
import dlackovi2_zadaca_3.model.Sensor;
import dlackovi2_zadaca_3.util.FileManager;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dlackovi2
 */
public class SlijednoAlgorithm extends Algorithm
{
    private FileManager fileManager = FileManager.getInstance();
    private PlaceIterator placeIterator = new PlaceIterator();

    @Override
    public void checkPlaces()
    {
        while (placeIterator.hasNext())
        {
            Place place = (Place) placeIterator.next();
            System.out.println("Provjeravam mjesto: " + place.getName() + "\n-------------------");
            
            try
            {
                place.setDevices(DeviceStatusChecker.checkStatus(place.getDevices()));
            }
            catch (IOException ex)
            {
                Logger.getLogger(SlijednoAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
