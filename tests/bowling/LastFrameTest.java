package bowling;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LastFrameTest
{
    private LastFrame lastFrame = new LastFrame();

    @Test
    public void lastFrameIsOverWithTwoRollsThatAreNotSpareOrStrike()
    {
        lastFrame.roll(1);
        lastFrame.roll(2);

        assertTrue(lastFrame.isOver());
    }

    @Test
    public void lastFrameIsOverWithThreeRollsWhenASpareIsRolled()
    {
        lastFrame.roll(5);
        lastFrame.roll(5);
        lastFrame.roll(5);

        assertTrue(lastFrame.isOver());
    }

    @Test
    public void lastFrameIsNotOverWithTwoRollsOfASpare()
    {
        lastFrame.roll(5);
        lastFrame.roll(5);

        assertFalse(lastFrame.isOver());
    }

    @Test
    public void lastFrameIsOverWithThreeRollsWhenAStrikeIsRolled()
    {
        lastFrame.roll(10);
        lastFrame.roll(3);
        lastFrame.roll(2);

        assertTrue(lastFrame.isOver());
    }

    @Test
    public void lastFrameIsNotOverOneRollOfAStrike()
    {
        lastFrame.roll(10);

        assertFalse(lastFrame.isOver());
    }

    @Test
    public void lastFrameIsNotOverWithOnlyOneBonusRollOfAStrike()
    {
        lastFrame.roll(10);
        lastFrame.roll(10);

        assertFalse(lastFrame.isOver());
    }

    @Test
    public void lastFrameScoresForTwoRollsWhenItIsNotSpareOrStrike()
    {
        lastFrame.roll(1);
        lastFrame.roll(2);

        assertThat(lastFrame.score(), equalTo(3));
    }

    @Test
    public void aSpareIsASpare()
    {
        lastFrame.roll(5);
        lastFrame.roll(5);

        assertTrue(lastFrame.isSpare());
    }

    @Test
    public void lastFrameScoresBonusFromThridRollWhenItIsASpare()
    {
        lastFrame.roll(5);
        lastFrame.roll(5);
        lastFrame.roll(5);

        assertThat(lastFrame.score(), equalTo(15));
    }

    @Test
    public void aStrikeIsAStrike()
    {
        lastFrame.roll(10);

        assertTrue(lastFrame.isStrike());
    }

    @Test
    public void aSpareIsNotAStrike()
    {
        lastFrame.roll(5);
        lastFrame.roll(5);

        assertFalse(lastFrame.isStrike());
    }

    @Test
    public void aStrikeIsNotASpare()
    {
        lastFrame.roll(10);

        assertTrue(lastFrame.isStrike());
    }

    @Test
    public void aStrikeScoresBonusFromSecondAndThridRolls()
    {
        lastFrame.roll(10);
        lastFrame.roll(5);
        lastFrame.roll(5);

        assertThat(lastFrame.score(), equalTo(20));
    }

    @Test
    public void spareBonusForPreviousFrameIsFirstRoll()
    {
        lastFrame.roll(5);
        lastFrame.roll(4);

        assertThat(lastFrame.bonusForSpareInPreviousFrame(), equalTo(5));
    }

    @Test
    public void strikeBonusForPreviousFrameIsFirstTwoRolls()
    {
        lastFrame.roll(5);
        lastFrame.roll(4);

        assertThat(lastFrame.bonusForStrikeInPreviousFrame(), equalTo(9));
    }

    @Test
    public void strikeBonusForFrame2StrikesAgoIsFirstRoll()
    {
        lastFrame.roll(5);
        lastFrame.roll(4);

        assertThat(lastFrame.bonus2ndRollForFirstOfTwoConsecutiveStrikes(), equalTo(5));
    }
}
