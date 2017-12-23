package dlackovi2_zadaca_3.validation;

import dlackovi2_zadaca_3.rng.RandomNumberGenerator;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dlackovi2
 */
public class ArgumentValidator2
{
    private int seed;
    private String placesFile;
    private String sensorsFile;
    private String actuatorsFile;
    private String algorithm;
    private int cycleDuration;
    private int nCycle;
    private String outputFile;
    private int outputBuffer;

    String args[];

    public ArgumentValidator2(String args[])
    {
        this.args = args;
    }

    public int getSeed()
    {
        return seed;
    }

    public void setSeed(int seed)
    {
        this.seed = seed;
    }

    public String getPlacesFile()
    {
        return placesFile;
    }

    public void setPlacesFile(String placesFile)
    {
        this.placesFile = placesFile;
    }

    public String getSensorsFile()
    {
        return sensorsFile;
    }

    public void setSensorsFile(String sensorsFile)
    {
        this.sensorsFile = sensorsFile;
    }

    public String getActuatorsFile()
    {
        return actuatorsFile;
    }

    public void setActuatorsFile(String actuatorsFile)
    {
        this.actuatorsFile = actuatorsFile;
    }

    public String getAlgorithm()
    {
        return algorithm;
    }

    public void setAlgorithm(String algorithm)
    {
        this.algorithm = algorithm;
    }

    public int getCycleDuration()
    {
        return cycleDuration;
    }

    public void setCycleDuration(int cycleDuration)
    {
        this.cycleDuration = cycleDuration;
    }

    public int getnCycle()
    {
        return nCycle;
    }

    public void setnCycle(int nCycle)
    {
        this.nCycle = nCycle;
    }

    public String getOutputFile()
    {
        return outputFile;
    }

    public void setOutputFile(String outputFile)
    {
        this.outputFile = outputFile;
    }

    public int getOutputBuffer()
    {
        return outputBuffer;
    }

    public void setOutputBuffer(int outputBuffer)
    {
        this.outputBuffer = outputBuffer;
    }

    public ValidArguments validate()
    {
        boolean hasError = false;

        System.out.println("Validating arguments...\n-----------------------");

        System.out.println("Working directory: " + System.getProperty("user.dir") + "\n");

        if (args.length == 1 && args[0].equals("--help"))
        {
            System.out.println("Kod izvršavanja programa upisuju se opcije, s time je njihov redoslije proizvoljan, a mogu biti sljedeće: \n-g sjeme za generator slučajnog broja (u intervalu 100 - 65535). Ako nije upisana opcija, uzima se broj milisekundi u trenutnom vremenu na bazi njegovog broja sekundi i broja milisekundi.\n-m naziv datoteke mjesta -s naziv datoteke senzora -a naziv datoteke aktuatora"
                    + "\n-alg puni naziv klase algoritma provjere koja se dinamički učitava \n-tcd trajanje ciklusa dretve u sek. Ako nije upisana opcija, uzima se slučajni broj u intervalu 1 - 17. \n-bcd broj ciklusa dretve. Ako nije upisana opcija, uzima se slučajni broj u intervalu 1 - 23. \n-i naziv datoteke u koju se sprema izlaz programa. Ako nije upisana opcija, uzima se vlastito korisničko ime kojem se dodaje trenutni podaci vremena po formatu _ggggmmdd_hhmmss.txt npr. dkermek_20171105_203128.txt \n-brl broj linija u spremniku za upis u datoteku za izlaz. Ako nije upisana opcija, uzima se slučajni broj u intervalu 100 - 999. \n--help pomoć za korištenje opcija u programu");
            System.exit(0);
        }

        List<String> checkList = Arrays.asList("-g", "-m", "-s", "-a", "-alg", "-tcd", "-bcd", "-i", "-brl");
        List<String> argsList = Arrays.asList(args);
        if (!argsList.containsAll(checkList))
        {
            System.out.println("Nepotpuni broj argumenata.");
            System.exit(0);
        }
        else
        {
            for (int i = 0; i < args.length; i++)
            {
                switch (args[i])
                {
                    case "-g":
                        System.out.print("Seed: ");
                        if (args[i + 1].startsWith("-"))//sto ako je zadnji element
                        {
                            //uzima se broj milisekundi u trenutnom vremenu na bazi njegovog broja sekundi i broja milisekundi.
                        }
                        else
                        {
                            int s = Integer.parseInt(args[i + 1]);
                            if (s >= 100 && s <= 65535)
                            {
                                System.out.println(s);
                                seed = s;
                            }
                            else
                            {
                                System.out.println("Error!");
                                hasError = true;
                            }
                        }
                        break;
                    case "-m":
                        System.out.print("Places: ");
                        if (!args[i + 1].startsWith("-") && Files.exists(Paths.get(args[i + 1])))
                        {
                            System.out.println(args[i + 1]);
                            placesFile = args[i + 1];
                        }
                        else
                        {
                            System.out.print("Error!");
                            hasError = true;
                        }
                        break;
                    case "-s":
                        System.out.print("Sensors: ");
                        if (!args[i + 1].startsWith("-") && Files.exists(Paths.get(args[i + 1])))
                        {
                            System.out.println(args[i + 1]);
                            sensorsFile = args[i + 1];
                        }
                        else
                        {
                            System.out.print("Error!");
                            hasError = true;
                        }
                        break;
                    case "-a":
                        System.out.print("Actuators: ");
                        if (!args[i + 1].startsWith("-") && Files.exists(Paths.get(args[i + 1])))
                        {
                            System.out.println(args[i + 1]);
                            actuatorsFile = args[i + 1];
                        }
                        else
                        {
                            System.out.print("Error!");
                            hasError = true;
                        }
                        break;
                    case "-alg":
                        List<String> algorithms = Arrays.asList("slijedno", "obrnuto", "random");
                        System.out.print("Algorithm: ");
                        if (!args[i + 1].startsWith("-") && algorithms.contains(args[i + 1]))
                        {
                            System.out.println(args[i + 1]);
                            algorithm = args[i + 1];
                        }
                        else
                        {
                            System.out.print("Error!");
                            hasError = true;
                        }
                        break;
                    case "-tcd":
                        System.out.print("Cycle duration: ");
                        if (args[i + 1].startsWith("-"))//sto ako je zadnji element
                        {
                            RandomNumberGenerator rng = RandomNumberGenerator.getInstance(seed);
                            cycleDuration = rng.dajSlucajniBroj(1, 17);
                            System.out.println(cycleDuration);
                        }
                        else if (Integer.parseInt(args[i + 1]) == (int) Integer.parseInt(args[i + 1]))
                        {
                            System.out.println(args[i + 1]);
                            cycleDuration = Integer.parseInt(args[i + 1]);
                        }
                        else
                        {
                            System.out.print("Error!");
                            hasError = true;
                        }
                        break;
                    case "-bcd":
                        System.out.print("Number of cycles: ");
                        if (args[i + 1].startsWith("-"))//sto ako je zadnji element
                        {
                            RandomNumberGenerator rng = RandomNumberGenerator.getInstance(seed);
                            nCycle = rng.dajSlucajniBroj(1, 23);
                            System.out.println(nCycle);
                        }
                        else if (Integer.parseInt(args[i + 1]) == (int) Integer.parseInt(args[i + 1]))
                        {
                            System.out.println(args[i + 1]);
                            nCycle = Integer.parseInt(args[i + 1]);
                        }
                        else
                        {
                            System.out.print("Error!");
                            hasError = true;
                        }
                        break;
                    case "-i":
                        System.out.print("Output: ");
                        if (args[i + 1].startsWith("-"))//sto ako je zadnji element
                        {
                            outputFile = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).replace(" ", "_").replace("-", "").replace(":", "");
                            System.out.println(outputFile);
                        }
                        else if (args[i + 1].endsWith(".txt"))
                        {
                            System.out.println(args[i + 1]);
                            outputFile = args[i + 1];
                        }
                        else
                        {
                            System.out.print("Error!");
                            hasError = true;
                        }
                        break;
                    case "-brl":
                        System.out.print("Output file buffer size: ");
                        if (args[i + 1].startsWith("-"))//sto ako je zadnji element
                        {
                            RandomNumberGenerator rng = RandomNumberGenerator.getInstance(seed);
                            outputBuffer = rng.dajSlucajniBroj(100, 999);
                            System.out.println(outputBuffer);
                        }
                        else
                        {
                            int s = Integer.parseInt(args[i + 1]);
                            if (s == (int) s)
                            {
                                outputBuffer = s;
                                System.out.println(outputBuffer);
                            }
                            else
                            {
                                System.out.println("Error!");
                                hasError = true;
                            }
                        }
                        break;
                }
            }

            if (hasError)
            {
                System.out.println("\nQuiting...");
                System.exit(0);
            }
            else
            {
                System.out.println("\nArguments OK.");
            }
        }
        
        return new ValidArguments(seed, placesFile, sensorsFile, actuatorsFile, algorithm, cycleDuration, nCycle, outputFile, outputBuffer);
    }
}
