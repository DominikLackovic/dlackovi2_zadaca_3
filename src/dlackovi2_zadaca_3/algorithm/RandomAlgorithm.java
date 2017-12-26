/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dlackovi2_zadaca_3.algorithm;

import dlackovi2_zadaca_3.Dlackovi2_zadaca_3;
import static dlackovi2_zadaca_3.Dlackovi2_zadaca_3.places;
import dlackovi2_zadaca_3.model.Device;
import dlackovi2_zadaca_3.model.Place;
import dlackovi2_zadaca_3.rng.RandomNumberGenerator;
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
public class RandomAlgorithm extends Algorithm
{
    private FileManager fileManager = FileManager.getInstance();
    private RandomNumberGenerator rng;
    private List<Integer> checkedIDs = new ArrayList<>();

    RandomAlgorithm(long seed)
    {
        this.rng = RandomNumberGenerator.getInstance(seed);
    }

    @Override
    public void checkPlaces()
    {
        for (int i = 0; i < places.size(); i++)
        {
            Integer nextID = rng.dajSlucajniBroj(0, places.size());
            while (checkedIDs.contains(nextID))
            {
                nextID = rng.dajSlucajniBroj(0, places.size());
            }

            checkedIDs.add(nextID);
            Place place = places.get(nextID);
            System.out.println("Provjeravam mjesto: " + place.getName() + "\n-------------------");
            
            try
            {
                place.setDevices(DeviceStatusChecker.checkStatus(place.getDevices()));
            }
            catch (IOException ex)
            {
                Logger.getLogger(RandomAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
