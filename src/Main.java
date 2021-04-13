import com.engine.AbstractApp;
import com.engine.Engine;
import com.engine.Renderer;

import java.awt.*;

public class Main extends AbstractApp {

    public Main() {

    }

    @Override
    public void update(Engine engine, float deltaTime) {

    }

    @Override
    public void render(Engine engine, Renderer renderer) {
        final Graphics2D g = renderer.g2d;
    }

    public static void main(String[] args) {
        Engine e = new Engine(new Main());
        e.setSize(800, 400);
        e.start();
    }
}
