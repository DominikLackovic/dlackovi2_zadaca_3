package dlackovi2_zadaca_3;

import dlackovi2_zadaca_3.algorithm.Algorithm;
import dlackovi2_zadaca_3.algorithm.AlgorithmFactory;
import dlackovi2_zadaca_3.algorithm.ConcreteAlgorithm;
import dlackovi2_zadaca_3.iterator.Container;
import dlackovi2_zadaca_3.iterator.Iterator;
import dlackovi2_zadaca_3.iterator.PlaceIterator;
import dlackovi2_zadaca_3.model.Actuator;
import dlackovi2_zadaca_3.model.Device;
import dlackovi2_zadaca_3.model.Place;
import dlackovi2_zadaca_3.model.Sensor;
import dlackovi2_zadaca_3.rng.RandomNumberGenerator;
import dlackovi2_zadaca_3.dretva.Dretva;
import dlackovi2_zadaca_3.util.FileManager;
import dlackovi2_zadaca_3.util.FileType;
import dlackovi2_zadaca_3.validation.ArgumentValidator;
import dlackovi2_zadaca_3.validation.ValidArguments;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

/**
 *
 * @author dlackovi2
 */
public class Dlackovi2_zadaca_3 implements Container
{
    public static List<Place> places = null;
    public static List<Sensor> sensors = null;
    public static List<Actuator> actuators = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, org.apache.commons.cli.ParseException, CloneNotSupportedException, IOException
    {
        ValidArguments validArguments = new ArgumentValidator().validate(args);
        FileManager fileManager = FileManager.getInstance();
//        FileManager.setOutputFile(validArguments.getOutputFile());
//        FileManager.setOutputBuffer(validArguments.getOutputBuffer());
//        FileManager.setSeed(validArguments.getSeed());
//        fileManager.exportData("- LOG DATOTEKA- " + System.lineSeparator() + "---------------" + System.lineSeparator());
//
//        places = (List<Place>) (List<?>) fileManager.importData(validArguments.getPlacesFile(), FileType.PLACE);
//        sensors = (List<Sensor>) (List<?>) fileManager.importData(validArguments.getSensorsFile(), FileType.SENSOR);
//        actuators = (List<Actuator>) (List<?>) fileManager.importData(validArguments.getActuatorsFile(), FileType.ACTUATOR);
//        RandomNumberGenerator rng = RandomNumberGenerator.getInstance(validArguments.getSeed());
//
//        Iterator iter = new PlaceIterator();
//        
//        //PRIDRUZIVANJE UREĐAJA MJESTIMA
//        List<Sensor> sensors0 = sensors.stream().filter(p -> p.getType() == 0).collect(Collectors.toList());
//        List<Sensor> sensors1 = sensors.stream().filter(p -> p.getType() == 1).collect(Collectors.toList());
//        List<Sensor> sensors2 = sensors.stream().filter(p -> p.getType() == 2).collect(Collectors.toList());
//
//        List<Actuator> actuators0 = actuators.stream().filter(p -> p.getType() == 0).collect(Collectors.toList());
//        List<Actuator> actuators1 = actuators.stream().filter(p -> p.getType() == 1).collect(Collectors.toList());
//        List<Actuator> actuators2 = actuators.stream().filter(p -> p.getType() == 2).collect(Collectors.toList());
//
//        for (Place p : places)
//        {
//            List<Device> devices = new ArrayList<Device>();
//            for (int i = 0; i < p.getnSensors(); i++)
//            {
//                Sensor s;
//                switch (p.getType())
//                {
//                    case 0:
//                        s = sensors0.get(rng.dajSlucajniBroj(0, sensors0.size()));
//                        s.setId(rng.dajJedinstveniBroj());
//                        devices.add(s);
//                        break;
//                    case 1:
//                        s = sensors1.get(rng.dajSlucajniBroj(0, sensors1.size()));
//                        s.setId(rng.dajJedinstveniBroj());
//                        devices.add(s);
//                        break;
//                }
//            }
//
//            for (int i = 0; i < p.getnActuators(); i++)
//            {
//                Actuator a;
//                switch (p.getType())
//                {
//                    case 0:
//                        a = actuators0.get(rng.dajSlucajniBroj(0, actuators0.size()));
//                        a.setId(rng.dajJedinstveniBroj());
//                        devices.add(a);
//                        break;
//                    case 1:
//                        a = actuators1.get(rng.dajSlucajniBroj(0, actuators1.size()));
//                        a.setId(rng.dajJedinstveniBroj());
//                        devices.add(a);
//                        break;
//                }
//            }
//
//            p.setDevices(devices);
//        }
//
//        //INICIJALIZACIJA SUSTAVA - provjera statusa
//        while (iter.hasNext())
//        {
//            Place place = (Place) iter.next();
//            for (Device device : place.getDevices())
//            {
//                device.checkStatus();
//                //System.out.println("Status: " + device.getStatus());
//            }
//        }
//
//        //PRIDRUZIVANJE SENZORA AKTUATORIMA
//        System.out.println("\n- PRIDRUZIVANJE SENZORA AKTUATORIMA -\n-------------------------------------");
//        fileManager.exportData(System.lineSeparator() + "- PRIDRUZIVANJE SENZORA AKTUATORIMA -" + System.lineSeparator() + "-------------------------------------");
//        String sensorsToActuators = "";
//        String actuatorsToSensors = "";
//        Iterator iter2 = new PlaceIterator();
//        while (iter2.hasNext())
//        {
//            Place place = (Place) iter2.next();
//            List<Device> devicesInsidePlace = place.getDevices();
//            List<Sensor> activeSensors = (List<Sensor>) (List<?>) devicesInsidePlace.stream().filter(p -> p.getClass().getTypeName().endsWith("Sensor") && p.getStatus() == true).collect(Collectors.toList());
//            List<Actuator> activeActuators = (List<Actuator>) (List<?>) devicesInsidePlace.stream().filter(p -> p.getClass().getTypeName().endsWith("Actuator") && p.getStatus() == true).collect(Collectors.toList());
//
//            if (activeSensors.size() > 0)
//            {
//                for (Device device : devicesInsidePlace)
//                {
//                    if (device instanceof Actuator)
//                    {
//                        Actuator act = (Actuator) device;
//                        if (act.getStatus())
//                        {
//                            List<Sensor> tempSensors = new ArrayList<>();
//                            for (int i = 0; i < rng.dajSlucajniBroj(1, activeSensors.size()); i++)
//                            {
//                                Sensor s = activeSensors.get(rng.dajSlucajniBroj(0, activeSensors.size()));
//                                tempSensors.add(s);
//                                sensorsToActuators += "Aktuatoru " + act.getName() + " pridruzen je senzor: " + s.getName() + System.lineSeparator();
//                                fileManager.exportData("Aktuatoru " + act.getName() + " pridruzen je senzor: " + s.getName());
//                                actuatorsToSensors += "Senzor " + s.getName() + " pridruzen je aktuatoru: " + act.getName() + System.lineSeparator();
//                                fileManager.exportData("Senzor " + s.getName() + " pridruzen je aktuatoru: " + act.getName());
//                            }
//                            act.setAttachedSensors(tempSensors);
//                        }
//                    }
//                }
//            }
//        }
//
//        //ISPIS PRIDRUZENIH
//        System.out.println("Rezultati pridruzivanja:");
//        System.out.println(sensorsToActuators);
//        System.out.println(actuatorsToSensors);
//        //fileManager.exportData(sensorsToActuators);
//        //fileManager.exportData(actuatorsToSensors);
//
//        Dretva dretva = new Dretva(validArguments.getSeed(), validArguments.getnCycle(), validArguments.getCycleDuration(), validArguments.getAlgorithm());
//        dretva.start();
    }

    @Override
    public Iterator getIterator()
    {
        return new PlaceIterator();
    }
}
