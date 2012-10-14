package bowling;

public class BowlingGame
{
    private static final int NUM_FRAMES = 10;
    private static final int PENULTIMATE_FRAME = NUM_FRAMES - 2;

    private Frame firstFrame;

    public BowlingGame()
    {
        Frame previousFrame = new LastFrame();

        for (int i = PENULTIMATE_FRAME; i >= 0 ; i--)
            previousFrame = new OrdinaryFrame(previousFrame);

        firstFrame = previousFrame;
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
