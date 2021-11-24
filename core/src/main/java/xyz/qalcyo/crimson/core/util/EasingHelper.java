/*
 * Crimson - The ultimate Minecraft library mod
 * Copyright (C) 2021 Qalcyo
 *
 * Crimson is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Crimson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Crimson. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.crimson.core.util;

/**
 * Different easings, view representations here: https://easings.net/
 * Should be used in things like UI animation.
 *
 * @author Basilicous
 */
public class EasingHelper {

    public double easeInSine(int n) {
        return 1 - Math.cos((n * Math.PI) / 2);
    }

    public double easeOutSine(int n) {
        return Math.sin((n * Math.PI) / 2);
    }

    public double easeInOutSine(int n) {
        return -(Math.cos(Math.PI * n) - 1) / 2;
    }

    public double easeInCubic(int n) {
        return n ^ 3;
    }

    public double easeOutCubic(int n) {
        return 1 - Math.pow(1 - n, 3);
    }

    public double easeInOutCubic(int n) {
        return n < 0.5 ? 4 * n ^ 3 : 1 - Math.pow(-2 * n + 2, 3) / 2;
    }

    public double easeInQuint(int n) {
        return n ^ 5;
    }

    public double easeOutQuint(int n) {
        return 1 - Math.pow(1 - n, 5);
    }

    public double easeInOutQuint(int n) {
        return n < 0.5 ? 16 * n ^ 5 : 1 - Math.pow(-2 * n + 2, 5) / 2;
    }

    public double easeInCirc(int n) {
        return 1 - Math.sqrt(1 - Math.pow(n, 2));
    }

    public double easeOutCirc(int n) {
        return Math.sqrt(1 - Math.pow(n - 1, 2));
    }

    public double easeInOutCirc(int n) {
        return n < 0.5 ? (1 - Math.sqrt(1 - Math.pow(2 * n, 2))) / 2 : (Math.sqrt(1 - Math.pow(-2 * n + 2, 2)) + 1) / 2;
    }

    public double easeInElastic(int n) {
        final double c4 = (2 * Math.PI) / 3;

        return n == 0 ? 0 : n == 1 ? 1 : -Math.pow(2, 10 * n - 10) * Math.sin((n * 10 - 10.75) * c4);

    }

    public double easeOutElastic(int n) {
        final double c4 = (2 * Math.PI) / 3;

        return n == 0 ? 0 : n == 1 ? 1 : Math.pow(2, -10 * n - 10) * Math.sin((n * 10 - 0.75) * c4) + 1;

    }

    public double easeInOutElastic(int n) {
        final double c5 = (2 * Math.PI) / 4.5;

        return n == 0 ? 0 : n == 1 ? 1 : n < 0.5 ? -(Math.pow(2, 20 * n - 10) * Math.sin((20 * n - 11.125) * c5)) / 2 : (Math.pow(2, -20 * n + 10) * Math.sin((20 * n - 11.125) * c5)) / 2 + 1;

    }

    public double easeInQuad(int n) {
        return n ^ 2;
    }

    public double easeOutQuad(int n) {
        return 1 - (1 - n) * (1 - n);
    }

    public double easeInOutQuad(int n) {
        return n < 0.5 ? 2 * n ^ 2 : 1 - Math.pow(-2 * n + 2, 2) / 2;
    }

    public double easeInQuart(int n) {
        return n ^ 4;
    }

    public double easeOutQuart(int n) {
        return 1 - Math.pow(1 - n, 4);
    }

    public double easeInOutQuart(int n) {
        return n < 0.5 ? 8 * n ^ 4 : 1 - Math.pow(-2 * n + 2, 4) / 2;
    }

    public double easeInExpo(int n) {
        return n == 0 ? 0 : Math.pow(2, 10 * n - 10);
    }

    public double easeOutExpo(int n) {
        return n == 1 ? 1 : 1 - Math.pow(2, -10 * n);
    }

    public double easeInOutExpo(int n) {
        return n == 0 ? 0 : n == 1 ? 1 : n < 0.5 ? Math.pow(2, 20 * n - 10) / 2 : (2 - Math.pow(2, -20 * n + 10)) / 2;
    }

    public double easeInBack(int n) {
        final double c1 = 1.70158;
        final double c3 = c1 + 1;

        return c3 * n * n * n - c1 * n * n;
    }

    public double easeOutBack(int n) {
        final double c1 = 1.70158;
        final double c3 = c1 + 1;

        return 1 + c3 * Math.pow(n - 1, 3) + c1 * Math.pow(n - 1, 2);
    }

    public double easeInOutBack(int n) {
        final double c1 = 1.70158;
        final double c2 = c1 * 1.525;

        return n < 0.5 ? (Math.pow(2 * n, 2) * ((c2 + 1) * 2 * n - c2)) / 2 : (Math.pow(2 * n - 2, 2) * ((c2 + 1) * (n * 2 - 2) + c2) + 2) / 2;
    }

    public double easeOutBounce(int n) {
        final double n1 = 7.5625;
        final double d1 = 2.75;

        if (n < 1 / d1) {
            return n1 * n * n;
        } else if (n < 2 / d1) {
            return n1 * (n -= 1.5 / d1) * n + 0.75;
        } else if (n < 2.5 / d1) {
            return n1 * (n -= 2.25 / d1) * n + 0.9375;
        } else {
            return n1 * (n -= 2.625 / d1) * n + 0.984375;
        }
    }

    public double easeInBounce(int n) {
        return 1 - easeOutBounce(1 - n);
    }

    public double easeInOutBounce(int n) {
        return n < 0.5 ? (1 - easeOutBounce(1 - 2 * n)) / 2 : (1 + easeOutBounce(2 * n - 1)) / 2;
    }

}