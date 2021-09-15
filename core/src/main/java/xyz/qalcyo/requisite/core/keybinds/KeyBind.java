package xyz.qalcyo.requisite.core.keybinds;

public abstract class KeyBind {

    private final String name, category;
    private int key;

    public KeyBind(String name, String category, int key) {
        this.name = name;
        this.category = category;
        this.key = key;
    }

    public abstract void handle(KeyBindState state);

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

}