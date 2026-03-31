package wolf.model;

public class Gun {
    public static final String GUN1  = "/sprites/gun1.png";
    public static final String GUN2  = "/sprites/gun2.png";
    public static final String GUN3  = "/sprites/gun3.png";
    public static final String GUN4  = "/sprites/gun4.png";
    public static final String GUN5  = "/sprites/gun5.png";

    private String[] sprites = {GUN1,GUN2,GUN3,GUN4,GUN5};

    private int ammo = 20;

    private boolean firing = false;

    private int aframe = 0;
    private int frames = 4;

    private int frameDelay = 4;
    private int frameTimer = 0;

    private int cooldownFrames = 20;
    private int cooldownTimer = 0;

    public void fire() {
        if (ammo==0) { return; }
        if (!firing && cooldownTimer==0) { 
            ammo--;
            firing = true;
            frameTimer = frameDelay;
        }
    }

    public void update() {
        if (firing) {
            if (frameTimer>0) {
                frameTimer--;
            } else {
                nextFrame();
                frameTimer = frameDelay;
            }
        }

        if (cooldownTimer > 0) { cooldownTimer--; } 
    }

    public void nextFrame() {
        aframe++;
        if (aframe>frames) { 
            aframe=0; 
            firing = false;
            cooldownTimer = cooldownFrames;
        }
    }

    public String getSprite() {
        return sprites[aframe];
    }

    public int getAmmo() {
        return ammo;
    }

    public boolean isFiring() {
        return firing;
    }
}
