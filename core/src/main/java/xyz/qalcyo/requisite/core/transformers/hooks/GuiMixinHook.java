/*
 * Requisite - Minecraft library mod
 *  Copyright (C) 2021 Qalcyo
 *
 * Requisite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Requisite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Requisite. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.requisite.core.transformers.hooks;

import gg.essential.elementa.components.Window;

public abstract class GuiMixinHook<T> extends MixinHook<T> {

    private final Window window;

    public GuiMixinHook(T instance, Window window) {
        super(instance);
        this.window = window;
    }

    public GuiMixinHook(T instance) {
        this(instance, new Window(144));
    }

    public void initialize() {
        window.onWindowResize();
    }

    public void draw(int mouseX, int mouseY, float partialTicks) {
        window.draw();
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        window.mouseClick(mouseX, mouseY, mouseButton);
    }

    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        window.mouseRelease();
    }

    public void mouseDrag() {
        window.mouseMove(window);
    }

    public Window getWindow() {
        return window;
    }

}