package dlackovi2_zadaca_3.validation;

/**
 *
 * @author dlackovi2
 */
public class ValidArguments
{
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

    public ValidArguments(int nRows, int nColumns, int nRowsCommands, int avgCorrectness, long seed, String placesFile, String sensorsFile, String actuatorsFile, String scheduleFile, int cycleDuration)
    {
        this.nRows = nRows;
        this.nColumns = nColumns;
        this.nRowsCommands = nRowsCommands;
        this.avgCorrectness = avgCorrectness;
        this.seed = seed;
        this.placesFile = placesFile;
        this.sensorsFile = sensorsFile;
        this.actuatorsFile = actuatorsFile;
        this.scheduleFile = scheduleFile;
        this.cycleDuration = cycleDuration;
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
}
