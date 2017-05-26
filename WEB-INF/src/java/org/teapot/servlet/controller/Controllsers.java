package org.teapot.servlet.controller;

public class Controllsers {
    private final static Controllsers INSTANCE = new Controllsers();

    private IAction[] actions = null;

    // Private constructor suppresses
    private Controllsers() {
        actions = new IAction[5];
        actions[0] = new Index(); // index
        actions[1] = new Login(); // login
        actions[2] = new Reg();
        actions[3] = new Logout();
        actions[4] = new MyPage();
    }

    // default public constructor
    public static Controllsers getInstance() {
        return INSTANCE;
    }
    public IAction getControllser(int i) {
        return actions[i];
    }
}
