package dlackovi2_zadaca_3.validation;

/**
 *
 * @author dlackovi2
 */
public class ValidArguments
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
    
    public ValidArguments(long seed, String placesFile, String sensorsFile, String actuatorsFile, String algorithm, int cycleDuration, int nCycle, String outputFile, int outputBuffer)
    {
        this.seed = seed;
        this.placesFile = placesFile;
        this.sensorsFile = sensorsFile;
        this.actuatorsFile = actuatorsFile;
        this.algorithm = algorithm;
        this.cycleDuration = cycleDuration;
        this.nCycle = nCycle;
        this.outputFile = outputFile;
        this.outputBuffer = outputBuffer;
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
    
    
}
