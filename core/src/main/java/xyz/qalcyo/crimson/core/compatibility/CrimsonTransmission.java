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

package xyz.qalcyo.crimson.core.compatibility;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.HashMap;
import java.util.Map;

public class CrimsonTransmission {

    private final Map<String, Object> storage = new HashMap<>();
    private final Cache<String, Object> cache = Caffeine.newBuilder().build();

    /**
     * Stores data in the transmission storage.
     *
     * @param id The ID of this item.
     * @param o The item to store under the ID provided.
     * @throws IllegalArgumentException If the storage already contains the ID provided.
     */
    public void add(String id, Object o) throws IllegalArgumentException {
        if (storage.containsKey(id)) {
            throw new IllegalArgumentException("The transmission storage already has an item under this ID!");
        }

        storage.put(id, o);
    }

    /**
     * Stores data in the transmission cache.
     *
     * @param id The ID of this item.
     * @param o The item to store under the ID provided.
     * @throws IllegalArgumentException If the storage already contains the ID provided.
     */
    public void cache(String id, Object o) throws IllegalArgumentException {
        if (cache.getIfPresent(id) != null) {
            throw new IllegalArgumentException("The transmission cache already has an item under this ID!");
        }

        cache.put(id, o);
    }

    /**
     * Removes an item from the transmission storage.
     *
     * @param id The ID of the item to remove.
     */
    public void remove(String id) {
        storage.remove(id);
    }

    /**
     * Removes an item from the transmission cache.
     *
     * @param id The ID of the item to remove.
     */
    public void invalidate(String id) {
        cache.invalidate(id);
    }

    /**
     * Gets an item from the transmission storage.
     *
     * @param id The ID of the item to get.
     * @return The item inside the storage under the ID.
     */
    public Object get(String id) {
        return storage.get(id);
    }

    /**
     * Gets an item from the transmission cache.
     *
     * @param id The ID of the item to get.
     * @return The item inside the cache under the ID.
     */
    public Object cached(String id) {
        return cache.getIfPresent(id);
    }

}