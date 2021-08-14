package xyz.matthewtgm.requisite.core.keybinds;

public abstract class KeyBind {

    private final String name;
    private final String category;
    private int key;

    public KeyBind(String name, String category, int key) {
        this.name = name;
        this.category = category;
        this.key = key;
    }

    public abstract void press();
    public abstract void hold();
    public abstract void release();

    public final void updateKey(int key) {
        this.key = key;
    }

    public final String getName() {
        return name;
    }

    public final String getCategory() {
        return category;
    }

    public final int getKey() {
        return key;
    }

}