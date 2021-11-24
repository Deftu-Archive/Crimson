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

public class MathHelper {
    /**
     * Clamps the provided value between 0.0 and 1.0
     * @return The clamped value.
     */
    public float clamp01(float value) {
        if ((double)value < 0.0)
            return 0.0f;
        return (double)value > 1.0 ? 1f : value;
    }

    /**
     * Clamps the provided value between params min and max.
     * @return The clamped value.
     */
    public float clamp(float val, float min, float max) {
        if (val > max) val = max;
        else if (val < min) val = min;
        return val;
    }

    /**
     * Clamps the provided value between params min and max.
     * @return The clamped value.
     */
    public int clamp_int(int num, int min, int max) {
        return num < min ? min : (Math.min(num, max));
    }

    /**
     * Clamps the provided value between params min and max.
     * @return The clamped value.
     */
    public long clamp_long(long num, long min, long max) {
        return num < min ? min : (Math.min(num, max));
    }

    /**
     * Lerps between params start and end at the rate of param interpolation.
     * @return The clamped value.
     */
    public float lerp(float start, float end, float interpolation) {
        return start + (end - start) * clamp01(interpolation);
    }
    public float percentageOf(float val, float min, float max) {
        return (val - min) / (max - min);
    }

    public int percentageOf_int(int val, int min, int max) {
        return (val - min) / (max - min);
    }

    /**
     * @return If param val is between params min and max
     */
    public boolean isBetween(int val, int min, int max) {
        return (val > min) && (val < max);
    }

}