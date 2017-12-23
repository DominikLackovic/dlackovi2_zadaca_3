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
    public static String outputFile;
    public static int outputBuffer;
    public static int outputBufferLength = 0;
    public static String outputText = "";
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
                        Place tempPlace = new PlaceBuilderImpl().setName(places[0]).setType(Integer.valueOf(places[1])).setnSensors(Integer.valueOf(places[2])).setnActuators(Integer.valueOf(places[3])).setUsable(true).setId(rng.dajJedinstveniBroj()).build();
                        
                        boolean placeExists = false;
                        for(Object p : data)
                        {
                            if(((Place) p).getName().equals(tempPlace.getName()))
                            {
                                exportData("Mjesto s imenom " + tempPlace.getName() + " vec postoji. Preskacem.");
                                System.out.println("Mjesto s imenom " + tempPlace.getName() + " vec postoji. Preskacem.");
                                placeExists = true;
                            }
                        }
                        
                        if(placeExists)
                            continue;
                        
                        if(tempPlace.getType() == 0 || tempPlace.getType() == 1) 
                        {
                            data.add(tempPlace);
                            exportData("Mjesto " + tempPlace.getName() + " uspjesno dodano.");
                            System.out.println("Mjesto " + tempPlace.getName() + " uspjesno dodano.");
                        }
                        else
                        {
                            exportData("Neispravno mjesto. Preskacem.");
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
                        tempSensor.setName(sensors[0]);
                        tempSensor.setType(Integer.valueOf(sensors[1]));
                        tempSensor.setKind(Integer.valueOf(sensors[2]));
                        tempSensor.setMinValue(Float.valueOf(sensors[3]));
                        tempSensor.setMaxValue(Float.valueOf(sensors[4]));
                        if (sensors.length >= 6)
                        {
                            tempSensor.setComment(sensors[5]);
                        }
                        
                        if((tempSensor.getType() >= 0 && tempSensor.getType() <= 2) && (tempSensor.getKind() >= 0 && tempSensor.getKind() <= 3)) 
                        {
                            exportData("Senzor " + tempSensor.getName() + " uspjesno dodan.");
                            System.out.println("Senzor " + tempSensor.getName() + " uspjesno dodan.");
                            data.add(tempSensor);
                        }                    
                        else
                        {
                            exportData("Neispravan senzor. Preskacem.");
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
                        tempActuator.setName(actuators[0]);
                        tempActuator.setType(Integer.valueOf(actuators[1]));
                        tempActuator.setKind(Integer.valueOf(actuators[2]));
                        tempActuator.setMinValue(Float.valueOf(actuators[3]));
                        tempActuator.setMaxValue(Float.valueOf(actuators[4]));
                        if (actuators.length >= 6)
                        {
                            tempActuator.setComment(actuators[5]);
                        }

                        if((tempActuator.getType() >= 0 && tempActuator.getType() <= 2) && (tempActuator.getKind() >= 0 && tempActuator.getKind() <= 3))  
                        {
                            exportData("Aktuator " + tempActuator.getName() + " uspjesno dodan.");
                            System.out.println("Aktuator " + tempActuator.getName() + " uspjesno dodan.");
                            data.add(tempActuator);
                        }    
                        else
                        {
                            exportData("Neispravan aktuator. Preskacem.");
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
    
    public void exportData(String message) throws IOException
    {
        outputBufferLength++;
        outputText += message + System.lineSeparator();
        
        if(outputBufferLength == outputBuffer)
        {
            Path path = Paths.get(outputFile);
            Files.write(path, (outputText + System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.C‌​REATE, StandardOpenOp‌​tion.APPEND);
            outputBufferLength = 0;
            outputText = "";
        }
    }
    
    public static int getOutputBufferLength()
    {
        return outputBufferLength;
    }

    public static void setOutputBufferLength(int outputBufferLength)
    {
        FileManager.outputBufferLength = outputBufferLength;
    }

    public static int getOutputBuffer()
    {
        return outputBuffer;
    }

    public static void setOutputBuffer(int outputBuffer)
    {
        FileManager.outputBuffer = outputBuffer;
    }

    public static String getOutputFile()
    {
        return outputFile;
    }

    public static void setOutputFile(String outputFile)
    {
        FileManager.outputFile = outputFile;
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
