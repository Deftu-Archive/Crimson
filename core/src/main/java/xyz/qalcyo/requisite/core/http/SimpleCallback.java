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

package xyz.qalcyo.requisite.core.http;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.function.BiConsumer;

public class SimpleCallback implements Callback {

    private BiConsumer<Call, Response> response;
    private BiConsumer<Call, IOException> failure;

    public SimpleCallback(BiConsumer<Call, Response> response, BiConsumer<Call, IOException> failure) {
        this.response = response;
        this.failure = failure;
    }

    public SimpleCallback(BiConsumer<Call, Response> response) {
        this(response, (call, exception) -> {});
    }

    public SimpleCallback() {
        this((call, response) -> {});
    }

    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        this.response.accept(call, response);
    }

    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        this.failure.accept(call, e);
    }

    public void setResponse(BiConsumer<Call, Response> response) {
        this.response = response;
    }

    public BiConsumer<Call, Response> getResponse() {
        return response;
    }

    public void setFailure(BiConsumer<Call, IOException> failure) {
        this.failure = failure;
    }

    public BiConsumer<Call, IOException> getFailure() {
        return failure;
    }

}