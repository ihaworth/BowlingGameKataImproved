package bowling;

public class BowlingGame
{
    private static final int NUM_FRAMES = 10;
    private static final int NUM_ORDINARY_FRAMES = NUM_FRAMES - 1;

    private Frame firstFrame;

    public BowlingGame()
    {
        Frame frame = new LastFrame();

        for (int i = 0 ; i < NUM_ORDINARY_FRAMES; i++)
            frame = new OrdinaryFrame(frame);

        firstFrame = frame;
    }

    public void roll(int numPins)
    {
        firstFrame.roll(numPins);
    }

    public int score()
    {
        return firstFrame.scoreFrameAndAnySubsequentFrames();
    }
}
