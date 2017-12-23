package dlackovi2_zadaca_3.iterator;

import static dlackovi2_zadaca_3.Dlackovi2_zadaca_3.places;
import dlackovi2_zadaca_3.iterator.Iterator;
import dlackovi2_zadaca_3.model.Place;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dlackovi2
 */
public class PlaceIterator implements Iterator
{
    private int nProcessed = 0;
    private int previous = 0;

    @Override
    public boolean hasNext()
    {
        if (nProcessed < places.size())
            return true;
        return false;
    }

    @Override
    public Object next()
    {
        if (this.hasNext())
        {
            nProcessed++;
            return places.get(getNextId());
        }
        return null;
    }

    private int getNextId()
    {
        List<Place> tempPlaces = new ArrayList<>();

        for (Place place : places)
        {
            if (place.getId() > previous)
            {
                tempPlaces.add(place);
            }
        }
        
        Place tempPlace = tempPlaces.get(0);
        for (Place greaterPlace : tempPlaces)
        {
            if (greaterPlace.getId() < tempPlace.getId())
            {
                tempPlace = greaterPlace;

            }
        }
        
        previous = tempPlace.getId();
        return places.indexOf(tempPlace);
    }
}
