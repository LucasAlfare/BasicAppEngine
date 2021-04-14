import com.engine.AbstractApp;
import com.engine.Engine;
import com.engine.Renderer;

import java.awt.Graphics2D;
import java.util.Random;

public class Main extends AbstractApp {

    int x, y;
    Random random;

    int red;
    int green;
    int blue;
    int alpha = 255;
    int color;

    Graphics2D g;

    public Main() {
        random = new Random();
    }

    @Override
    public void update(Engine engine, float deltaTime) {

    }

    @Override
    public void render(Engine engine, Renderer renderer) {
        g = renderer.g2d;

        for (int i = 0; i < engine.getWidth(); i++) {
            for (int j = 0; j < engine.getHeight(); j++) {
                red = random.nextInt(255);
                green = random.nextInt(255);
                blue = random.nextInt(255);

                color = (((((red << 8) | green) << 8) | blue) << 8) | alpha;

                renderer.setPixel(i, j, color);
            }
        }
    }

    public static void main(String[] args) {
        Engine e = new Engine(new Main());
        e.setSize(500, 500);
        e.start();
    }
}
