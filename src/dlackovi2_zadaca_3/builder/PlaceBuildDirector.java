package dlackovi2_zadaca_3.builder;

import dlackovi2_zadaca_3.model.Place;

/**
 *
 * @author dlackovi2
 */
public class PlaceBuildDirector
{
    private PlaceBuilder builder;

    public PlaceBuildDirector(final PlaceBuilder builder)
    {
        this.builder = builder;
    }

    public Place construct()
    {
        return builder.build();
    }
}
