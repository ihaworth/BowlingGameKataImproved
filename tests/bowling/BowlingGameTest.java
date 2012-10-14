package bowling;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BowlingGameTest
{
    private BowlingGame bowlingGame = new BowlingGame();

    @Test
    public void gutterGameScoresZero()
    {
        rollManyZeroFrames(10);

        assertThat(bowlingGame.score(), equalTo(0));
    }

    @Test
    public void onePinWithEveryBallScores20()
    {
        rollMany(1, 20);

        assertThat(bowlingGame.score(), equalTo(20));
    }

    @Test
    public void spareScoresBonusFromNextRoll()
    {
        rollSpare();

        bowlingGame.roll(1);
        bowlingGame.roll(0);

        rollManyZeroFrames(8);

        assertThat(bowlingGame.score(), equalTo(11 + 1));
    }

    @Test
    public void strikeScoresBonusFromNextRoll()
    {
        rollStrike();

        bowlingGame.roll(1);
        bowlingGame.roll(2);

        rollManyZeroFrames(8);

        assertThat(bowlingGame.score(), equalTo(13 + 3));
    }

    @Test
    public void twoStrikesScoreBonusFromNextTwoFrames()
    {
        rollStrike();

        rollStrike();

        bowlingGame.roll(1);
        bowlingGame.roll(0);

        rollManyZeroFrames(7);

        assertThat(bowlingGame.score(), equalTo(21 + 11 + 1));
    }

    @Test
    public void lastFrameIsThreeRollsWhenASpare()
    {
        rollManyZeroFrames(9);

        rollSpare();
        bowlingGame.roll(1);

        assertThat(bowlingGame.score(), equalTo(11));
    }

    @Test
    public void spareInPenultimateFrameGetsBonusFromFinalFrame()
    {
        rollManyZeroFrames(8);

        rollSpare();

        bowlingGame.roll(1);
        bowlingGame.roll(2);

        assertThat(bowlingGame.score(), equalTo(11 + 3));
    }

    @Test
    public void strikeInPenultimateFrameGetsBonusFromFinalFrame()
    {
        rollManyZeroFrames(8);

        rollStrike();

        bowlingGame.roll(1);
        bowlingGame.roll(2);

        assertThat(bowlingGame.score(), equalTo(13 + 3));
    }

    @Test
    public void strikesInPenultimateAndPrePenultimateFramesGetsBonusFromFinalFrame()
    {
        rollManyZeroFrames(7);

        rollStrike();

        rollStrike();

        bowlingGame.roll(1);
        bowlingGame.roll(2);

        assertThat(bowlingGame.score(), equalTo(21 + 13 + 3));
    }

    @Test
    public void perfectGameScores300()
    {
        rollMany(10, 12);

        assertThat(bowlingGame.score(), equalTo(300));
    }

    private void rollManyZeroFrames(int numFrames)
    {
        for (int i = 0; i < numFrames; i++)
            rollZeroFrame();
    }

    private void rollZeroFrame()
    {
        bowlingGame.roll(0);
        bowlingGame.roll(0);
    }

    private void rollMany(int value, int num)
    {
        for (int i = 0; i < num; i++)
        {
            bowlingGame.roll(value);
        }
    }

    private void rollSpare()
    {
        bowlingGame.roll(5);
        bowlingGame.roll(5);
    }

    private void rollStrike()
    {
        bowlingGame.roll(10);
    }
}
