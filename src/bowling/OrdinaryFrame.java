package bowling;

public class OrdinaryFrame implements Frame
{
    private static final int ROLL_UNMADE = -1;

    private int firstRoll = ROLL_UNMADE;
    private int secondRoll = ROLL_UNMADE;

    private Frame nextFrame;

    public OrdinaryFrame(Frame nextFrame)
    {
        this.nextFrame = nextFrame;
    }

    @Override
    public void roll(int numPins)
    {
        if (firstRoll == ROLL_UNMADE)
            firstRoll = numPins;
        else if (!isStrike() && secondRoll == ROLL_UNMADE)
            secondRoll = numPins;
        else
            nextFrame.roll(numPins);
    }

    @Override
    public int score()
    {
        if (isSpare())
            return 10 + nextFrame.bonusForSpareInPreviousFrame();

        if (isStrike())
            return 10 + nextFrame.bonusForStrikeInPreviousFrame();

        return firstRoll + secondRoll;
    }


    @Override
    public int scoreFrameAndAnySubsequentFrames()
    {
        return score() + nextFrame.scoreFrameAndAnySubsequentFrames();
    }

    @Override
    public boolean isOver()
    {
        return secondRoll != ROLL_UNMADE || isStrike();
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
        if (isStrike())
            return 10 + nextFrame.bonus2ndRollForFirstOfTwoConsecutiveStrikes();

        return firstRoll + secondRoll;
    }

    @Override
    public int bonus2ndRollForFirstOfTwoConsecutiveStrikes()
    {
        return firstRoll;
    }
}
