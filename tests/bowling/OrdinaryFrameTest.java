package bowling;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrdinaryFrameTest
{
    private OrdinaryFrame frame;
    private OrdinaryFrame nextFrame;
    private OrdinaryFrame nextButOneFrame;

    @Before
    public void setUp()
    {
        nextButOneFrame = new OrdinaryFrame(null);
        nextFrame = new OrdinaryFrame(nextButOneFrame);
        frame = new OrdinaryFrame(nextFrame);
    }

    @Test
    public void after2RollsTheFrameIsOver()
    {
        frame.roll(1);
        frame.roll(2);

        assertTrue(frame.isOver());
    }

    @Test
    public void theScoreIsTheSumOfThe2Rolls()
    {
        frame.roll(1);
        frame.roll(2);

        assertThat(frame.score(), equalTo(3));
    }

    @Test
    public void aSpareIsASpare()
    {
        frame.roll(5);
        frame.roll(5);

        assertTrue(frame.isSpare());
    }

    @Test
    public void spareScoreIncludesNextRollAsBonus()
    {
        frame.roll(5);
        frame.roll(5);

        nextFrame.roll(3);

        assertThat(frame.score(), equalTo(10 + 3));
    }

    @Test
    public void aStrikeIsAStrike()
    {
        frame.roll(10);

        assertTrue(frame.isStrike());
    }

    @Test
    public void aStrikeMeansTheFrameIsOver()
    {
        frame.roll(10);

        assertTrue(frame.isOver());
    }

    @Test
    public void strikeScoreIncludesNext2RollsAsBonus()
    {
        frame.roll(10);

        nextFrame.roll(3);
        nextFrame.roll(6);

        assertThat(frame.score(), equalTo(10 + 9));
    }

    @Test
    public void scoreForTwoSucessiveStrikesIncludesRollsFromNext2FramesAsBonus()
    {
        frame.roll(10);
        nextFrame.roll(10);
        nextButOneFrame.roll(6);

        assertThat(frame.score(), equalTo(10 + 10 + 6));
    }
}
