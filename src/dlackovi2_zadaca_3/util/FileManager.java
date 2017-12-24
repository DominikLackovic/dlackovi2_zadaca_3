package dlackovi2_zadaca_3.util;

import dlackovi2_zadaca_3.builder.PlaceBuilderImpl;
import dlackovi2_zadaca_3.model.Actuator;
import dlackovi2_zadaca_3.model.Place;
import dlackovi2_zadaca_3.model.Sensor;
import dlackovi2_zadaca_3.rng.RandomNumberGenerator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dlackovi2
 */
public class FileManager
{
    private static volatile FileManager INSTANCE;
    public static long seed;

    private FileManager()
    {
    }

    public static FileManager getInstance()
    {
        if (INSTANCE == null)
        {
            synchronized (FileManager.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = new FileManager();
                }
            }
        }
        return INSTANCE;
    }

    public List<Object> importData(String fileName, FileType type) throws CloneNotSupportedException
    {
        List<Object> data = new ArrayList<>();
        Place placePrototype = new Place();
        Sensor sensorPrototype = new Sensor();
        Actuator actuatorPrototype = new Actuator();
        RandomNumberGenerator rng = RandomNumberGenerator.getInstance(seed);

        BufferedReader br = null;
        String line = "";
        String delimiter = ";";

        try
        {
            br = new BufferedReader(new FileReader(fileName));
            System.out.println(fileName);
            switch (type)
            {
                case PLACE:
                    br.readLine();
                    while ((line = br.readLine()) != null)
                    {
                        String[] places = line.split(delimiter);
                        Place tempPlace = new PlaceBuilderImpl()
                                .setId(Integer.valueOf(places[0]))
                                .setName(places[1]).setType(Integer.valueOf(places[2]))
                                .setnSensors(Integer.valueOf(places[3]))
                                .setnActuators(Integer.valueOf(places[4]))
                                .setUsable(true)
                                .build();
                        
                        boolean placeExists = false;
                        for(Object p : data)
                        {
                            if(((Place) p).getName().equals(tempPlace.getName()))
                            {
                                System.out.println("Mjesto s imenom " + tempPlace.getName() + " vec postoji. Preskacem.");
                                placeExists = true;
                            }
                        }
                        
                        if(placeExists)
                            continue;
                        
                        if(tempPlace.getType() == 0 || tempPlace.getType() == 1) 
                        {
                            data.add(tempPlace);
                            System.out.println("Mjesto " + tempPlace.getName() + " uspjesno dodano.");
                        }
                        else
                        {
                            System.out.println("Neispravno mjesto. Preskacem.");
                        }   
                    }
                    break;
                case SENSOR:
                    br.readLine();
                    while ((line = br.readLine()) != null)
                    {
                        Sensor tempSensor = (Sensor) sensorPrototype.clone();
                        String[] sensors = line.split(delimiter);
                        tempSensor.setId(Integer.valueOf(sensors[0]));
                        tempSensor.setName(sensors[1]);
                        tempSensor.setType(Integer.valueOf(sensors[2]));
                        tempSensor.setKind(Integer.valueOf(sensors[3]));
                        tempSensor.setMinValue(Float.valueOf(sensors[4]));
                        tempSensor.setMaxValue(Float.valueOf(sensors[5]));
                        if (sensors.length >= 6)
                        {
                            tempSensor.setComment(sensors[6]);
                        }
                        
                        if((tempSensor.getType() >= 0 && tempSensor.getType() <= 2) && (tempSensor.getKind() >= 0 && tempSensor.getKind() <= 3)) 
                        {     
                            System.out.println("Senzor " + tempSensor.getName() + " uspjesno dodan.");
                            data.add(tempSensor);
                        }                    
                        else
                        {
                            System.out.println("Neispravan senzor. Preskacem.");
                        } 
                    }
                    break;
                case ACTUATOR:
                    br.readLine();
                    while ((line = br.readLine()) != null)
                    {
                        Actuator tempActuator = new Actuator();
                        String[] actuators = line.split(delimiter);
                        tempActuator.setId(Integer.valueOf(actuators[0]));
                        tempActuator.setName(actuators[1]);
                        tempActuator.setType(Integer.valueOf(actuators[2]));
                        tempActuator.setKind(Integer.valueOf(actuators[3]));
                        tempActuator.setMinValue(Float.valueOf(actuators[4]));
                        tempActuator.setMaxValue(Float.valueOf(actuators[5]));
                        if (actuators.length >= 6)
                        {
                            tempActuator.setComment(actuators[6]);
                        }

                        if((tempActuator.getType() >= 0 && tempActuator.getType() <= 2) && (tempActuator.getKind() >= 0 && tempActuator.getKind() <= 3))  
                        {
                            System.out.println("Aktuator " + tempActuator.getName() + " uspjesno dodan.");
                            data.add(tempActuator);
                        }    
                        else
                        {
                            System.out.println("Neispravan aktuator. Preskacem.");
                        } 
                    }
                    break;
                default:
            }
        }
        catch (IOException | NumberFormatException ex)
        {

        }

        return data;
    }

    public static long getSeed()
    {
        return seed;
    }

    public static void setSeed(long seed)
    {
        FileManager.seed = seed;
    }
}
