package nl.tdegroot.games.rain;

import nl.tdegroot.games.pixxel.PixxelGame;
import nl.tdegroot.games.pixxel.gfx.Screen;
import nl.tdegroot.games.pixxel.util.Random;

import java.util.ArrayList;
import java.util.List;

public class RainGame extends PixxelGame {

    private int spawnTimer;
    private int rainDropCount = 6;
    private List<RainDrop> rainDrops = new ArrayList<RainDrop>();

    public RainGame(String title, int width, int height, int scale) {
        super(title, width, height, scale);
    }

    public void init() {

    }

    public void tick(int delta) {
        spawnTimer -= delta;
        if (spawnTimer <= 0) {
            for (int i = 0; i < rainDropCount; i++) {
                int x = Random.nextInt(0, display.width / display.scale);
                int y = -50;
                double speed = Random.nextDouble();
                int color = Random.nextInt(0xFF000055, 0xFF0000FF);
                width = Random.nextInt(2, 6);
                height = Random.nextInt(10, 14);
                RainDrop rainDrop = new RainDrop(x, y, width, height, speed, color);
                rainDrops.add(rainDrop);
            }
            spawnTimer = 2000;
        }

        if (rainDrops.size() <= 0) return;

        for(int i = 0; i < rainDrops.size(); i++) {
            if (rainDrops.get(i).finished) {
                rainDrops.remove(i);
                continue;
            }
            RainDrop drop = rainDrops.get(i);
            drop.tick(delta / 200);
            if (drop.x >= display.width / display.scale || drop.y >= display.height / display.scale)
                drop.finished = true;
        }
    }

    public void render() {
        Screen screen = display.screen;
        for(RainDrop drop : rainDrops) {
            drop.render(screen);
        }
    }

    public static void main(String[] args) {
        RainGame game = new RainGame("Make it rain!", 1280, 720, 8);
        game.start();
    }

}
