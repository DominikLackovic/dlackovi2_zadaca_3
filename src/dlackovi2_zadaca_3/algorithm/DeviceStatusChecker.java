package dlackovi2_zadaca_3.algorithm;

import static dlackovi2_zadaca_3.Dlackovi2_zadaca_3.actuators;
import static dlackovi2_zadaca_3.Dlackovi2_zadaca_3.sensors;
import dlackovi2_zadaca_3.model.Actuator;
import dlackovi2_zadaca_3.model.Device;
import dlackovi2_zadaca_3.model.Sensor;
import dlackovi2_zadaca_3.rng.RandomNumberGenerator;
import dlackovi2_zadaca_3.util.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dlackovi2
 */
public class DeviceStatusChecker
{
    public static List<Device> checkStatus(List<Device> devices) throws IOException
    {
        FileManager fileManager = FileManager.getInstance();
        List<Device> invalidDevices = new ArrayList<>();
        List<Device> substitutionDevices = new ArrayList<>();
        for (Device device : devices)
        {
            if (!device.checkStatus())
            {
                if (device instanceof Actuator)
                {
                    Actuator actuator = (Actuator) device;
                    System.out.print("Provjeravam aktuator: " + actuator.getId() + " " + actuator.getName());
                    fileManager.exportData("Provjeravam aktuator: " + actuator.getId() + " " + actuator.getName());
                }
                else
                {
                    Sensor sensor = (Sensor) device;
                    System.out.print("Provjeravam senzor: " + sensor.getId() + " " + sensor.getName());
                    fileManager.exportData("Provjeravam senzor: " + sensor.getId() + " " + sensor.getName());
                }
                
                System.out.println("\nBroj pogresaka za uredaj: " + device.getnErrors());
                fileManager.exportData("Broj pogresaka za uredaj: " + device.getnErrors());
                if (device.getnErrors() >= 3)
                {
                    device.setnErrors(0);
                    if (device instanceof Sensor)
                    {
                        Sensor sensor = null;
                        RandomNumberGenerator rng = RandomNumberGenerator.getInstance(999);
                        while (sensor == null || sensor.getType() != ((Sensor) device).getType())
                        {
                            sensor = sensors.get(rng.dajSlucajniBroj(0, sensors.size() - 1));
                        }

                        invalidDevices.add(device);
                        substitutionDevices.add(sensor);
                        System.out.println("Zamjena uredaja: " + ((Sensor) device).getName() + " => " + sensor.getName());
                        fileManager.exportData("Zamjena uredaja: " + ((Sensor) device).getName() + " => " + sensor.getName());
                    }
                    
                    if (device instanceof Actuator)
                    {
                        Actuator actuator = null;
                        RandomNumberGenerator rng = RandomNumberGenerator.getInstance(999);
                        while (actuator == null || actuator.getType() != ((Actuator) device).getType())
                        {
                            actuator = actuators.get(rng.dajSlucajniBroj(0, actuators.size() - 1));
                        }

                        invalidDevices.add(device);
                        substitutionDevices.add(actuator);
                        System.out.println("Zamjena uredaja: " + ((Actuator) device).getName() + " => " + actuator.getName());
                        fileManager.exportData("Zamjena uredaja: " + ((Actuator) device).getName() + " => " + actuator.getName());
                    }
                }
            }
            else
            {
                if (device instanceof Actuator)
                {
                    Actuator actuator = (Actuator) device;
                    System.out.print("Provjeravam aktuator: " + actuator.getId() + " " + actuator.getName());
                    fileManager.exportData("Provjeravam aktuator: " + actuator.getId() + " " + actuator.getName());
                    System.out.println(" => Ispravan uređaj.");
                    fileManager.exportData(" => Ispravan uređaj.");
                }
                else
                {
                    Sensor sensor = (Sensor) device;
                    System.out.print("Provjeravam senzor: " + sensor.getId() + " " + sensor.getName());
                    fileManager.exportData("Provjeravam senzor: " + sensor.getId() + " " + sensor.getName());
                    System.out.println(" => Ispravan uređaj.");
                    fileManager.exportData(" => Ispravan uređaj.");
                }  
            }
        }
        
        devices.removeAll(invalidDevices);
        devices.addAll(substitutionDevices);
        
        return devices;
    }
}
