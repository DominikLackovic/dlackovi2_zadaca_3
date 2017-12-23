package dlackovi2_zadaca_3.algorithm;

import dlackovi2_zadaca_3.Dlackovi2_zadaca_3;
import static dlackovi2_zadaca_3.Dlackovi2_zadaca_3.places;
import dlackovi2_zadaca_3.iterator.PlaceIterator;
import dlackovi2_zadaca_3.model.Place;
import dlackovi2_zadaca_3.util.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dlackovi2
 */
public class ObrnutoAlgorithm extends Algorithm
{
    private List<Integer> placeIDs = new ArrayList<>();
    private FileManager fileManager = FileManager.getInstance();

    @Override
    public void checkPlaces()
    {
        PlaceIterator iterator = new PlaceIterator();

        while (iterator.hasNext())
        {
            placeIDs.add(((Place) iterator.next()).getId());
        }

        for (Integer placeID : placeIDs)
        {
            iterator = new PlaceIterator();
            while (iterator.hasNext())
            {
                Place place = (Place) iterator.next();
                if (place.getId() == placeID)
                {
                    System.out.println("Provjeravam mjesto: " + place.getName() + "\n-------------------");
                    try
                    {
                        fileManager.exportData("Provjeravam mjesto: " + place.getName() + System.lineSeparator() + "-------------------");
                    }
                    catch (IOException ex)
                    {
                        Logger.getLogger(ObrnutoAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try
                    {
                        place.setDevices(DeviceStatusChecker.checkStatus(place.getDevices()));
                    }
                    catch (IOException ex)
                    {
                        Logger.getLogger(ObrnutoAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
