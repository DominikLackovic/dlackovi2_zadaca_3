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
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author dlackovi2
 */
public class ArgumentValidator
{
    private RandomNumberGenerator rng;
    private int nRows;
    private int nColumns;
    private int nRowsCommands;
    private int avgCorrectness;
    private long seed;
    private String placesFile;
    private String sensorsFile;
    private String actuatorsFile;
    private String scheduleFile;
    private int cycleDuration;

    private final Options options;
    private final CommandLineParser parser;

    public ArgumentValidator()
    {
        this.rng = RandomNumberGenerator.getInstance(seed);
        this.parser = new DefaultParser();
        this.options = new Options();
        
        Option br = OptionBuilder.withArgName("br")
                                .hasArg()
                                .withDescription("Broj redaka na ekranu (24-40)")
                                .create("br");
        
        Option bs = OptionBuilder.withArgName("bs")
                                .hasArg()
                                .withDescription("Broj stupaca na ekranu (80-160)")
                                .create("bs");
        
        Option brk = OptionBuilder.withArgName("brk")
                                .hasArg()
                                .withDescription("Broj redaka na ekranu za unos komandi (2-5)")
                                .create("brk");
        
        Option pi = OptionBuilder.withArgName("pi")
                                .hasArg()
                                .withDescription("Prosječni % ispravnosti uređaja (0-100)")
                                .create("pi");
        
        Option g = OptionBuilder.withArgName("g")
                                .hasArg()
                                .withDescription("Sjeme za generator slučajnog broja (u intervalu 100 - 65535)")
                                .create("g");
        
        Option m = OptionBuilder.withArgName("m")
                                .hasArg()
                                .withDescription("Naziv datoteke mjesta")
                                .create("m");
        
        Option s = OptionBuilder.withArgName("s")
                                .hasArg()
                                .withDescription("Naziv datoteke senzora")
                                .create("s");
        
        Option a = OptionBuilder.withArgName("a")
                                .hasArg()
                                .withDescription("Naziv datoteke aktuatora")
                                .create("a");
        
        Option r = OptionBuilder.withArgName("r")
                                .hasArg()
                                .withDescription("Naziv datoteke rasporeda")
                                .create("r");
        
        Option tcd = OptionBuilder.withArgName("tcd")
                                .hasArg()
                                .withDescription("Trajanje ciklusa dretve u sek")
                                .create("tcd");
        
        options.addOption(br);
        options.addOption(bs);
        options.addOption(brk);
        options.addOption(pi);
        options.addOption(g);
        options.addOption(m);
        options.addOption(s);
        options.addOption(a);
        options.addOption(r);
        options.addOption(tcd);
    }

    public RandomNumberGenerator getRng()
    {
        return rng;
    }

    public void setRng(RandomNumberGenerator rng)
    {
        this.rng = rng;
    }

    public int getnRows()
    {
        return nRows;
    }

    public void setnRows(int nRows)
    {
        this.nRows = nRows;
    }

    public int getnColumns()
    {
        return nColumns;
    }

    public void setnColumns(int nColumns)
    {
        this.nColumns = nColumns;
    }

    public int getnRowsCommands()
    {
        return nRowsCommands;
    }

    public void setnRowsCommands(int nRowsCommands)
    {
        this.nRowsCommands = nRowsCommands;
    }

    public int getAvgCorrectness()
    {
        return avgCorrectness;
    }

    public void setAvgCorrectness(int avgCorrectness)
    {
        this.avgCorrectness = avgCorrectness;
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

    public String getScheduleFile()
    {
        return scheduleFile;
    }

    public void setScheduleFile(String scheduleFile)
    {
        this.scheduleFile = scheduleFile;
    }

    public int getCycleDuration()
    {
        return cycleDuration;
    }

    public void setCycleDuration(int cycleDuration)
    {
        this.cycleDuration = cycleDuration;
    }

    public ValidArguments validate(final String[] args) throws ParseException
    {
        boolean hasError = false;

        System.out.println("Validating arguments...\n-----------------------");

        System.out.println("Working directory: " + System.getProperty("user.dir") + "\n");
        
        if(args.length == 0)
        {
            System.out.println("Missing arguments!");
            System.exit(0);
        }

        if (args.length == 1 && args[0].equals("--help"))
        {
            String message = "-br broj redaka na ekranu (24-40)" + System.lineSeparator()
                    + "-bs broj stupaca na ekranu (80-160)" + System.lineSeparator()
                    + "-brk broj redaka na ekranu za unos komandi (2-5)" + System.lineSeparator()
                    + "-pi prosječni % ispravnosti uređaja (0-100)" + System.lineSeparator()
                    + "-g sjeme za generator slučajnog broja (u intervalu 100 - 65535)"  + System.lineSeparator()
                    + "-m naziv datoteke mjesta" + System.lineSeparator()
                    + "-s naziv datoteke senzora" + System.lineSeparator()
                    + "-a naziv datoteke aktuatora" + System.lineSeparator()
                    + "-r naziv datoteke rasporeda" + System.lineSeparator()
                    + "-tcd trajanje ciklusa dretve u sek" + System.lineSeparator()
                    + "--help pomoć za korištenje opcija u programu" + System.lineSeparator();
             
            System.exit(0);
        }
 
        CommandLine cmd = parser.parse(options, args);
       
        System.out.print("Number of rows: ");
        if (cmd.getOptionValue("br") == null)
        {
            nRows = 24;
            System.out.println(seed);
        }
        else
        {
            int s = Integer.valueOf(cmd.getOptionValue("br"));
            if (s >= 24 && s <= 40)
            {
                nRows = s;
                System.out.println(seed);
            }
            else
            {
                System.out.println("Error!");
                hasError = true;
            }
        }
        
        System.out.print("Number of columns: ");
        if (cmd.getOptionValue("bs") == null)
        {
            nColumns = 80;
            System.out.println(nColumns);
        }
        else
        {
            int s = Integer.valueOf(cmd.getOptionValue("bs"));
            if (s >= 80 && s <= 160)
            {
                nColumns = s;
                System.out.println(nColumns);
            }
            else
            {
                System.out.println("Error!");
                hasError = true;
            }
        }
        
        System.out.print("Number of rows for commands: ");
        if (cmd.getOptionValue("brk") == null)
        {
            nRowsCommands = 2;
            System.out.println(nColumns);
        }
        else
        {
            int s = Integer.valueOf(cmd.getOptionValue("brk"));
            if (s >= 2 && s <= 5)
            {
                nRowsCommands = s;
                System.out.println(nRowsCommands);
            }
            else
            {
                System.out.println("Error!");
                hasError = true;
            }
        }
        
        System.out.print("Average correctness: ");
        if (cmd.getOptionValue("pi") == null)
        {
            avgCorrectness = 50;
            System.out.println(avgCorrectness);
        }
        else
        {
            int s = Integer.valueOf(cmd.getOptionValue("pi"));
            if (s >= 0 && s <= 100)
            {
                avgCorrectness = s;
                System.out.println(avgCorrectness);
            }
            else
            {
                System.out.println("Error!");
                hasError = true;
            }
        }

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
        
        System.out.print("Schedule: ");
        if (cmd.getOptionValue("r") != null && Files.exists(Paths.get(cmd.getOptionValue("r"))))
        {
            scheduleFile = cmd.getOptionValue("r");
            System.out.println(scheduleFile);
        }
        else
        {
            System.out.println("Error!");
            hasError = true;
        } 

        System.out.print("Cycle duration: ");
        if (cmd.getOptionValue("tcd") == null)
        {
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

        if (hasError)
        {
            System.out.println("\nQuiting...");
            System.exit(0);
        }
        else
        {
            System.out.println("\nArguments OK.");
        }

        return new ValidArguments(nRows, nColumns, nRowsCommands, avgCorrectness, seed, placesFile, sensorsFile, actuatorsFile, scheduleFile, cycleDuration);
    }
}
