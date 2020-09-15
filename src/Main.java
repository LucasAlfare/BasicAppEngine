import com.engine.AbstractApplication;
import com.engine.Engine;
import com.engine.Renderer;

public class Main extends AbstractApplication {

    public Main() {

    }

    @Override
    public void update(Engine engine, float deltaTime) {

    }

    @Override
    public void render(Engine engine, Renderer renderer) {

    }

    public static void main(String[] args) {
        Engine e = new Engine(new Main());
        e.setSize(800, 400);
        e.start();
    }
}
