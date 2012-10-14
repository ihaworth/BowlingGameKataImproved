package bowling;

public interface Frame
{
    void roll(int numPins);
    int score();

    boolean isOver();
    boolean isSpare();
    boolean isStrike();

    int bonusForSpareInPreviousFrame();
    int bonusForStrikeInPreviousFrame();
    int bonus2ndRollForFirstOfTwoConsecutiveStrikes();
}
