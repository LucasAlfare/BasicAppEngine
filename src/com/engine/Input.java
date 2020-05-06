package com.engine;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    public static final int NUM_KEYS = 256;
    public static final int NUM_BUTTONS = 5;

    private Engine engine;

    private static boolean[] keys, lastKeys, buttons, lastButtons;
    private boolean mouseMoving;

    public static int mouseX = -1, mouseY = -1, lastMouseX = -1, lastMouseY = -1;
    public static int mouseScroll;

    public Input(Engine engine) {
        this.engine = engine;

        keys = new boolean[NUM_KEYS];
        lastKeys = new boolean[NUM_KEYS];

        buttons = new boolean[NUM_BUTTONS];
        lastButtons = new boolean[NUM_BUTTONS];

        engine.getWindow().getCanvas().addKeyListener(this);
        engine.getWindow().getCanvas().addMouseListener(this);
        engine.getWindow().getCanvas().addMouseMotionListener(this);
        engine.getWindow().getCanvas().addMouseWheelListener(this);
        engine.getWindow().getCanvas().requestFocus();
    }

    public void update() {
        mouseScroll = 0;
        System.arraycopy(keys, 0, lastKeys, 0, NUM_KEYS);
        System.arraycopy(buttons, 0, lastButtons, 0, NUM_BUTTONS);
        lastMouseX = mouseX;
        lastMouseY = mouseY;
    }

    public static boolean isKey(int keyCode) {
        return keys[keyCode];
    }

    public static boolean isKeyUp(int keyCode) {
        return !keys[keyCode] && lastKeys[keyCode];
    }

    public static boolean isKeyDown(int keyCode) {
        return keys[keyCode] && !lastKeys[keyCode];
    }

    public static boolean isButton(int button) {
        return buttons[button];
    }

    public static boolean isButtonUp(int button) {
        return !buttons[button] && lastButtons[button];
    }

    public static boolean isButtonDown(int button) {
        return buttons[button] && !lastButtons[button];
    }

    public static boolean isLeft() {
        return isKey(KeyEvent.VK_LEFT) || isKey(KeyEvent.VK_A);
    }

    public static boolean isLeftDown() {
        return isKeyDown(KeyEvent.VK_LEFT) || isKeyDown(KeyEvent.VK_A);
    }

    public static boolean isLeftUp() {
        return isKeyUp(KeyEvent.VK_LEFT) || isKeyUp(KeyEvent.VK_A);
    }

    public static boolean isRight() {
        return isKey(KeyEvent.VK_RIGHT) || isKey(KeyEvent.VK_D);
    }

    public static boolean isRightDown() {
        return isKeyDown(KeyEvent.VK_RIGHT) || isKeyDown(KeyEvent.VK_D);
    }

    public static boolean isRightUp() {
        return isKeyUp(KeyEvent.VK_RIGHT) || isKeyUp(KeyEvent.VK_D);
    }

    public static boolean isUp() {
        return isKey(KeyEvent.VK_UP) || isKey(KeyEvent.VK_W);
    }

    public static boolean isUpDown() {
        return isKeyDown(KeyEvent.VK_UP) || isKeyDown(KeyEvent.VK_W);
    }

    public static boolean isUpUp() {
        return isKeyUp(KeyEvent.VK_UP) || isKeyUp(KeyEvent.VK_W);
    }

    public static boolean isDown() {
        return isKey(KeyEvent.VK_DOWN) || isKey(KeyEvent.VK_S);
    }

    public static boolean isDownDown() {
        return isKeyDown(KeyEvent.VK_DOWN) || isKeyDown(KeyEvent.VK_S);
    }

    public static boolean isDownUp() {
        return isKeyUp(KeyEvent.VK_DOWN) || isKeyUp(KeyEvent.VK_S);
    }

    public static boolean isMouseButton(int button) {
        return buttons[button];
    }

    public static boolean isMouseButtonUp(int button) {
        return !buttons[button] && lastButtons[button];
    }

    public static boolean isMouseButtonDown(int button) {
        return buttons[button] && !lastButtons[button];
    }

    public static boolean isMouseMoving() {
        return (mouseX != lastMouseX) || (mouseY != lastMouseY);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseMoving = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseMoving = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = (int) (e.getX() / engine.getScale());
        mouseY = (int) (e.getY() / engine.getScale());
        mouseMoving = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (int) (e.getX() / engine.getScale());
        mouseY = (int) (e.getY() / engine.getScale());
        mouseMoving = true;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        mouseScroll = e.getWheelRotation();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getMouseScroll() {
        return mouseScroll;
    }
}
