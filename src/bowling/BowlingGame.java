package bowling;

public class BowlingGame
{
    private static final int NUM_FRAMES = 10;

    private static final int PENULTIMATE_FRAME = NUM_FRAMES - 2;

    private static final int LAST_FRAME = NUM_FRAMES - 1;

    private Frame[] frames = new Frame[NUM_FRAMES];
    private int currentFrame = 0;

    public BowlingGame()
    {
        frames[LAST_FRAME] = new LastFrame();

        for (int i = PENULTIMATE_FRAME; i >= 0 ; i--)
            frames[i] = new OrdinaryFrame(frames[i + 1]);
    }

    public void roll(int numPins)
    {
        currentFrame().roll(numPins);

        if (currentFrame().isOver())
        {
            currentFrame++;
        }
    }

    private Frame currentFrame()
    {
        return frames[currentFrame];
    }

    public int score()
    {
        int score = 0;

        for (int i = 0; i < NUM_FRAMES; i++)
        {
            score += frames[i].score();
        }
        return score;
    }
}
