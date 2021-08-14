package xyz.matthewtgm.requisite.core.data;

public interface IScreenPosition {
    float getX();
    IScreenPosition setX(float x);
    float getY();
    IScreenPosition setY(float y);

    IScreenPosition setPosition(float x, float y);
}