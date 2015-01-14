package nl.tdegroot.games.rain;

import nl.tdegroot.games.pixxel.gfx.Screen;
import nl.tdegroot.games.pixxel.gfx.Sprite;
import nl.tdegroot.games.pixxel.util.Log;

public class RainDrop {

    public int x;
    public int y;
    private final int width;
    private final int height;
    private double speed;
    private Sprite sprite;

    public boolean finished;

    public RainDrop(int x, int y, int width, int height, double speed, int color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;

        sprite = new Sprite(color, width, height);
    }

    public void tick(int delta) {
        int mass = (int) ((width * height) * 0.05);
        int dy = ((int) Math.ceil(mass * speed + 0.8));
        y += dy;
    }

    public void render(Screen screen) {
        screen.render(x, y, sprite);
    }

}
