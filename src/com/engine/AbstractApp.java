package com.engine;

public abstract class AbstractApp {

    private long last;
    private volatile boolean started = false;

    public abstract void update(Engine engine, float deltaTime);

    public abstract void render(Engine engine, Renderer renderer);

    public void updateWithInternalDelta(Engine engine) {
        if (!started) {
            started = true;
            last = System.nanoTime();
        }

        long now = System.nanoTime();
        float mDelta = (now - last) / 1e9f;
        last = now;

        this.update(engine, mDelta);
    }
}
