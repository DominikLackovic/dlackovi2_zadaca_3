package dlackovi2_zadaca_3.model;

import dlackovi2_zadaca_3.rng.RandomNumberGenerator;

/**
 *
 * @author dlackovi2
 */
public class DeviceRandomStatus implements Device
{
    private int nErrors = 0;
    private boolean usable;
    RandomNumberGenerator rng = RandomNumberGenerator.getInstance(958);

    @Override
    public boolean checkStatus()
    {
        int randomNumber = rng.dajSlucajniBroj(0, 100);

        if (randomNumber < 90)
        {
            nErrors = 0;
            usable = true;
            return true;
        }
        else
        {
            nErrors++;
            usable = false;
            return false;
        }
    }

    @Override
    public boolean getStatus()
    {
        return usable;
    }

    @Override
    public int getnErrors()
    {
        return nErrors;
    }

    @Override
    public void setnErrors(int n)
    {
        this.nErrors = n;
    }

    
}
