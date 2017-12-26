package dlackovi2_zadaca_3.dretva;

import dlackovi2_zadaca_3.algorithm.Algorithm;
import dlackovi2_zadaca_3.algorithm.ConcreteAlgorithm;
import dlackovi2_zadaca_3.iterator.PlaceIterator;
import dlackovi2_zadaca_3.model.Actuator;
import dlackovi2_zadaca_3.model.Device;
import dlackovi2_zadaca_3.model.Place;
import dlackovi2_zadaca_3.model.Sensor;
import dlackovi2_zadaca_3.rng.RandomNumberGenerator;
import dlackovi2_zadaca_3.util.FileManager;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dlackovi2
 */
public class Dretva extends Thread
{
    private long seed;
    private int nCycle = 0;
    private int cycleDuration = 0;
    private int onCycle = 0;
    private boolean stop = false;
    private String selectedAlgorithm;
    private RandomNumberGenerator rng;
    private FileManager fileManager = FileManager.getInstance();

    public Dretva(long seed, int nCycle, int cycleDuration, String algorithm)
    {
        this.nCycle = nCycle;
        this.cycleDuration = cycleDuration;
        this.seed = seed;
        this.selectedAlgorithm = algorithm;
        rng = RandomNumberGenerator.getInstance(seed);
    }

    @Override
    public void interrupt()
    {
        stop = true;
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run()
    {

        while (!stop)
        {
            Date processingStart = new Date();
            onCycle++;
            
            System.out.println("Ciklus: " + onCycle);
            
            if (selectedAlgorithm.equals("") || selectedAlgorithm == null || !selectedAlgorithm.equals("slijedno") || !selectedAlgorithm.equals("random") || !selectedAlgorithm.equals("obrnuto"))
            {
                selectedAlgorithm = "slijedno";
            }
            
            System.out.println("PROVJERA: " + selectedAlgorithm);
            Algorithm algorithm = new ConcreteAlgorithm().createAlgorithm(selectedAlgorithm, seed);
            algorithm.checkPlaces();

            PlaceIterator iterator = new PlaceIterator();
            while (iterator.hasNext())
            {
                Place place = (Place) iterator.next();
                try
                {
                    checkDevicesValues(place.getDevices());
                }
                catch (IOException ex)
                {
                    Logger.getLogger(Dretva.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            Date processingEnd = new Date();
            long processingDuration = processingEnd.getTime() - (processingStart.getTime() - 1);
            if (processingDuration < 0)
            {
                processingDuration = 0;
            }
            try
            {
                sleep((cycleDuration * 1000) - processingDuration);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Dretva.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (onCycle > nCycle)
            {
                stop = true;
            }
        }
    }

    @Override
    public synchronized void start()
    {
        System.out.println("Dretva radi...");
        
        super.start();
    }

    public void checkDevicesValues(List<Device> devices) throws IOException
    {
        for (Device device : devices)
        {
            if (device instanceof Sensor)
            {
                Sensor sensor = (Sensor) device;
                System.out.println("Stanje - Sensor: " + sensor.getName() + " => " + sensor.getValue());
                
                switch (sensor.getKind())
                {
                    case 0:
                        int value = (int) rng.dajSlucajniBroj(sensor.getMinValue(), sensor.getMaxValue());
                        if (sensor.isAscend())
                        {
                            if ((sensor.getValue() + value) >= sensor.getMaxValue())
                            {
                                sensor.setValue(value);
                                sensor.setAscend(false);
                            }
                            else
                            {
                                sensor.setValue(sensor.getValue() + value);
                            }
                        }
                        else
                        {
                            if ((sensor.getValue() - value) <= sensor.getMinValue())
                            {
                                sensor.setValue(sensor.getMinValue());
                                sensor.setAscend(true);
                            }
                            else
                            {
                                sensor.setValue(sensor.getValue() - value);
                            }
                        }
                        break;
                    case 1:
                        float floatValue = rng.dajSlucajniBroj(sensor.getMinValue(), sensor.getMaxValue());
                        double val = Math.round(floatValue * 10) / 10.0;
                        floatValue = (float) val;
                        if (sensor.isAscend())
                        {
                            if ((sensor.getValue() + floatValue) >= sensor.getMaxValue())
                            {
                                sensor.setValue(floatValue);
                                sensor.setAscend(false);
                            }
                            else
                            {
                                sensor.setValue(sensor.getValue() + floatValue);
                            }
                        }
                        else
                        {
                            if ((sensor.getValue() - floatValue) <= sensor.getMinValue())
                            {
                                sensor.setValue(sensor.getMinValue());
                                sensor.setAscend(true);
                            }
                            else
                            {
                                sensor.setValue(sensor.getValue() - floatValue);
                            }
                        }
                        break;
                    case 2:
                        float floatValue5 = rng.dajSlucajniBroj(sensor.getMinValue(), sensor.getMaxValue());
                        double valu = Math.round(floatValue5 * 10000) / 10.00000;
                        floatValue5 = (float) valu;
                        if (sensor.isAscend())
                        {
                            if ((sensor.getValue() + floatValue5) >= sensor.getMaxValue())
                            {
                                sensor.setValue(floatValue5);
                                sensor.setAscend(false);
                            }
                            else
                            {
                                sensor.setValue(sensor.getValue() + floatValue5);
                            }
                        }
                        else
                        {
                            if ((sensor.getValue() - floatValue5) <= sensor.getMinValue())
                            {
                                sensor.setValue(sensor.getMinValue());
                                sensor.setAscend(true);
                            }
                            else
                            {
                                sensor.setValue(sensor.getValue() - floatValue5);
                            }
                        }
                        break;
                    case 3:
                        if (sensor.getValue() == 1)
                        {
                            sensor.setValue(0);
                        }
                        else
                        {
                            sensor.setValue(1);
                        }
                        break;

                }
            }
            else
            {
                if (device instanceof Actuator)
                {
                    Actuator actuator = (Actuator) device;
                    System.out.println("Stanje - Aktuator: " + actuator.getName() + " => " + actuator.getValue());
                    
                    switch (actuator.getKind())
                    {
                        case 0:
                            int value = (int) rng.dajSlucajniBroj(actuator.getMinValue(), actuator.getMaxValue());
                            if (actuator.isAscend())
                            {
                                if ((actuator.getValue() + value) >= actuator.getMaxValue())
                                {
                                    actuator.setValue(value);
                                    actuator.setAscend(false);
                                }
                                else
                                {
                                    actuator.setValue(actuator.getValue() + value);
                                }
                            }
                            else
                            {
                                if ((actuator.getValue() - value) <= actuator.getMinValue())
                                {
                                    actuator.setValue(actuator.getMinValue());
                                    actuator.setAscend(true);
                                }
                                else
                                {
                                    actuator.setValue(actuator.getValue() - value);
                                }
                            }
                            break;
                        case 1:
                            float floatValue = rng.dajSlucajniBroj(actuator.getMinValue(), actuator.getMaxValue());
                            double val = Math.round(floatValue * 10) / 10.0;
                            floatValue = (float) val;
                            if (actuator.isAscend())
                            {
                                if ((actuator.getValue() + floatValue) >= actuator.getMaxValue())
                                {
                                    actuator.setValue(floatValue);
                                    actuator.setAscend(false);
                                }
                                else
                                {
                                    actuator.setValue(actuator.getValue() + floatValue);
                                }
                            }
                            else
                            {
                                if ((actuator.getValue() - floatValue) <= actuator.getMinValue())
                                {
                                    actuator.setValue(actuator.getMinValue());
                                    actuator.setAscend(true);
                                }
                                else
                                {
                                    actuator.setValue(actuator.getValue() - floatValue);
                                }
                            }
                            break;
                        case 2:
                            float floatValue5 = rng.dajSlucajniBroj(actuator.getMinValue(), actuator.getMaxValue());
                            double valu = Math.round(floatValue5 * 10000) / 10.00000;
                            floatValue5 = (float) valu;
                            if (actuator.isAscend())
                            {
                                if ((actuator.getValue() + floatValue5) >= actuator.getMaxValue())
                                {
                                    actuator.setValue(floatValue5);
                                    actuator.setAscend(false);
                                }
                                else
                                {
                                    actuator.setValue(actuator.getValue() + floatValue5);
                                }
                            }
                            else
                            {
                                if ((actuator.getValue() - floatValue5) <= actuator.getMinValue())
                                {
                                    actuator.setValue(actuator.getMinValue());
                                    actuator.setAscend(true);
                                }
                                else
                                {
                                    actuator.setValue(actuator.getValue() - floatValue5);
                                }
                            }
                            break;
                        case 3:
                            if (actuator.getValue() == 1)
                            {
                                actuator.setValue(0);
                            }
                            else
                            {
                                actuator.setValue(1);
                            }
                            break;
                    }
                }
            }
        }
    }
}
