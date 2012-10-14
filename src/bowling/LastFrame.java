package bowling;

public class LastFrame implements Frame
{
    private static final int ROLL_UNMADE = -1;

    private int firstRoll = ROLL_UNMADE;
    private int secondRoll = ROLL_UNMADE;
    private int thridRoll = ROLL_UNMADE;

    @Override
    public void roll(int numPins)
    {
        if (firstRoll == ROLL_UNMADE)
            firstRoll = numPins;
        else if (secondRoll == ROLL_UNMADE)
            secondRoll = numPins;
        else
            thridRoll = numPins;
    }

    @Override
    public boolean isOver()
    {
        if (isSpare() || isStrike())
            return firstRoll != ROLL_UNMADE && secondRoll != ROLL_UNMADE && thridRoll != ROLL_UNMADE;

        return firstRoll != ROLL_UNMADE && secondRoll != ROLL_UNMADE;
    }

    int score()
    {
        if (isSpare() || isStrike())
            return firstRoll + secondRoll + thridRoll;

        return firstRoll + secondRoll;
    }


    @Override
    public int scoreFrameAndAnySubsequentFrames()
    {
        return score();
    }

    @Override
    public boolean isSpare()
    {
        return firstRoll + secondRoll == 10;
    }

    @Override
    public boolean isStrike()
    {
        return firstRoll == 10;
    }


    @Override
    public int bonusForSpareInPreviousFrame()
    {
        return firstRoll;
    }

    @Override
    public int bonusForStrikeInPreviousFrame()
    {
        return firstRoll + secondRoll;
    }

    @Override
    public int bonus2ndRollForFirstOfTwoConsecutiveStrikes()
    {
        return firstRoll;
    }
}
