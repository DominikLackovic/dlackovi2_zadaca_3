package dlackovi2_zadaca_3.validation;

import dlackovi2_zadaca_3.rng.RandomNumberGenerator;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author dlackovi2
 */
public class ArgumentValidator
{

    private long seed;
    private String placesFile;
    private String sensorsFile;
    private String actuatorsFile;
    private String algorithm;
    private int cycleDuration;
    private int nCycle;
    private String outputFile;
    private int outputBuffer;

    private final Options options;
    private final CommandLineParser parser;

    public ArgumentValidator()
    {
        this.parser = new DefaultParser();
        this.options = new Options();
        options.addOption("g", true, "Arg1");
        options.addOption("m", true, "Arg2");
        options.addOption("s", true, "Arg3");
        options.addOption("a", true, "Arg4");
        options.addOption("alg", true, "Arg5");
        options.addOption("tcd", true, "Arg6");
        options.addOption("bcd", true, "Arg7");
        options.addOption("i", true, "Arg8");
        options.addOption("brl", true, "Arg9");
    }

    public long getSeed()
    {
        return seed;
    }

    public void setSeed(long seed)
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

    public ValidArguments validate(final String[] args) throws ParseException
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
 
        CommandLine cmd = parser.parse(options, args);
        List<String> checkList = Arrays.asList("-g", "-m", "-s", "-a", "-alg", "-tcd", "-bcd", "-i", "-brl");
        List<String> argsList = Arrays.asList(args);
        /*if (!argsList.containsAll(checkList))
        {
            System.out.println("Nepotpuni broj argumenata.");
            System.exit(0);
        }*/

        System.out.print("Seed: ");
        if (cmd.getOptionValue("g") == null)
        {
            //uzima se broj milisekundi u trenutnom vremenu na bazi njegovog broja sekundi i broja milisekundi.
            seed = System.currentTimeMillis() % 1000;
            System.out.println(seed);
        }
        else
        {
            long s = Long.valueOf(cmd.getOptionValue("g"));
            if (s >= 100 && s <= 65535)
            {
                seed = s;
                System.out.println(seed);
            }
            else
            {
                System.out.println("Error!");
                hasError = true;
            }
        }

        System.out.print("Places: ");
        if (cmd.getOptionValue("m") != null && Files.exists(Paths.get(cmd.getOptionValue("m"))))
        {
            placesFile = cmd.getOptionValue("m");
            System.out.println(placesFile);
        }
        else
        {
            System.out.println("Error!");
            hasError = true;
        }

        System.out.print("Sensors: ");
        if (cmd.getOptionValue("s") != null && Files.exists(Paths.get(cmd.getOptionValue("s"))))
        {
            sensorsFile = cmd.getOptionValue("s");
            System.out.println(sensorsFile);
        }
        else
        {
            System.out.println("Error!");
            hasError = true;
        }

        System.out.print("Actuators: ");
        if (cmd.getOptionValue("a") != null && Files.exists(Paths.get(cmd.getOptionValue("a"))))
        {
            actuatorsFile = cmd.getOptionValue("a");
            System.out.println(actuatorsFile);
        }
        else
        {
            System.out.println("Error!");
            hasError = true;
        }

        List<String> algorithms = Arrays.asList("slijedno", "obrnuto", "random");
        System.out.print("Algorithm: ");
        if (cmd.getOptionValue("alg") != null && algorithms.contains(cmd.getOptionValue("alg")))
        {
            algorithm = cmd.getOptionValue("alg");
            System.out.println(algorithm);
        }
        else
        {
            System.out.println("Error!");
            hasError = true;
        }

        System.out.print("Cycle duration: ");
        if (cmd.getOptionValue("tcd") == null)
        {
            RandomNumberGenerator rng = RandomNumberGenerator.getInstance(seed);
            cycleDuration = rng.dajSlucajniBroj(1, 17);
            System.out.println(cycleDuration);
        }
        else
        {
            if (Integer.valueOf(cmd.getOptionValue("tcd")) == (int) Integer.valueOf(cmd.getOptionValue("tcd")))
            {
                cycleDuration = Integer.valueOf(cmd.getOptionValue("tcd"));
                System.out.println(cycleDuration);
            }
            else
            {
                System.out.println("Error!");
                hasError = true;
            }
        }

        System.out.print("Number of cycles: ");
        if (cmd.getOptionValue("bcd") == null)
        {
            RandomNumberGenerator rng = RandomNumberGenerator.getInstance(seed);
            nCycle = rng.dajSlucajniBroj(1, 23);
            System.out.println(nCycle);
        }
        else
        {
            if (Integer.valueOf(cmd.getOptionValue("bcd")) == (int) Integer.valueOf(cmd.getOptionValue("bcd")))
            {
                nCycle = Integer.valueOf(cmd.getOptionValue("bcd"));
                System.out.println(nCycle);
            }
            else
            {
                System.out.println("Error!");
                hasError = true;
            }
        }

        System.out.print("Output: ");
        if (cmd.getOptionValue("i") == null)
        {
            outputFile = "dlackovi2_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).replace(" ", "_").replace("-", "").replace(":", "");
            System.out.println(outputFile);
        }
        else
        {
            if (cmd.getOptionValue("i").endsWith(".txt"))
            {
                outputFile = cmd.getOptionValue("i");
                System.out.println(outputFile);
            }
            else
            {
                System.out.println("Error!");
                hasError = true;
            }
        }

        System.out.print("Output file buffer size: ");
        if (cmd.getOptionValue("brl") == null)
        {
            RandomNumberGenerator rng = RandomNumberGenerator.getInstance(seed);
            outputBuffer = rng.dajSlucajniBroj(100, 999);
            System.out.println(outputBuffer);
        }
        else
        {
            int d = Integer.valueOf(cmd.getOptionValue("brl"));
            if (d == (int) d)
            {
                outputBuffer = d;
                System.out.println(outputBuffer);
            }
            else
            {
                System.out.println("Error!");
                hasError = true;
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

        return new ValidArguments(seed, placesFile, sensorsFile, actuatorsFile, algorithm, cycleDuration, nCycle, outputFile, outputBuffer);
    }
}
