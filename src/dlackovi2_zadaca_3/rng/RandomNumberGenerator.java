package dlackovi2_zadaca_3.rng;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author dlackovi2
 */
public class RandomNumberGenerator
{
    private static volatile RandomNumberGenerator INSTANCE;
    private Random rand;
    private List<Integer> randomNumbersPlace = new ArrayList<>();

    private RandomNumberGenerator()
    {
    }

    public static RandomNumberGenerator getInstance(long seed)
    {
        if (INSTANCE == null)
        {
            synchronized (RandomNumberGenerator.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = new RandomNumberGenerator();
                }
            }
        }
        
        INSTANCE.rand = new Random(seed);
        return INSTANCE;
    }
    
    //[odBroja, doBroja>
    public int dajSlucajniBroj(int odBroja, int doBroja)
    {
        if(odBroja == 1 && doBroja == 1)
            return 1;
        else
            return rand.nextInt(doBroja - odBroja) + odBroja;
    }
    
    //[odBroja, doBroja>
    public float dajSlucajniBroj(float odBroja, float doBroja)
    {
        return rand.nextFloat() * (doBroja - odBroja) + odBroja;
    }
    
    public int dajJedinstveniBroj()
    {
        while(true)
        {
            Integer number = dajSlucajniBroj(1, 1000);
            if(!randomNumbersPlace.contains(number))
            {
                randomNumbersPlace.add(number);
                return number;
            }
        }
    }
}
