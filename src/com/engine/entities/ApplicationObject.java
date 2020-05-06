package com.engine.entities;

import com.engine.Engine;
import com.engine.Renderer;

public abstract class ApplicationObject {

    protected float x, y;
    protected int width, height;
    protected boolean dead = false;
    protected String tag;

    public abstract void update(Engine engine, float deltaTime);

    public abstract void render(Engine engine, Renderer renderer);

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
