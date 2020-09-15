package com.engine;

public class Engine implements Runnable {

    public static final int RATE = 60;

    private int width;
    private int height;

    private float scale;

    int frames = 0, updates = 0;
    private boolean running;

    private Window window;
    private Renderer renderer;
    private Input input;
    private final AbstractApplication application;

    public Engine(AbstractApplication application) {
        this.application = application;
        setScale(1f);
    }

    public void start() {
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);

        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        running = true;

        final double ns = 1e9 / RATE;
        long now;
        long auxTimer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        float delta = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) { update(); delta--; }

            //render here
            renderGame();

            //helper to measure rates per second elapsed
            if (System.currentTimeMillis() - auxTimer >= 1000) {
                window.getFrame().setTitle(frames + " FPS | " + updates + " UPS");
                frames = 0;
                updates = 0;
                auxTimer = System.currentTimeMillis();
            }
        }
    }

    public void update() {
        application.updateWithInternalDelta(this);
        input.update();
        updates++;
    }

    public void renderGame() {
        renderer.clear();
        application.render(this, renderer);
        window.render();
        frames++;
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Window getWindow() {
        return window;
    }
}
