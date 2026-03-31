package wolf.model;

public class Animator {
    private String[] frames;
    private int aframe, frameTimer, frameDelay;
    private boolean finished;

    Animator(String[] frames, int frameDelay) {
        this.frames = frames;
        this.frameDelay = frameDelay;
        this.frameTimer = frameDelay;
    }

    public void update() {
        if (finished) { return; }
        if (frameTimer > 0) { 
            frameTimer--; 
        } else { 
            aframe++;
            if (aframe>=frames.length) {
                aframe = frames.length-1;
                finished = true;
            }
            frameTimer = frameDelay; 
        }
    }

    public void reset() {
        aframe = 0;
        frameTimer = frameDelay;
        finished = false;
    }

    public boolean isFinished() { return finished; }
    public String current() { return frames[aframe]; }
}