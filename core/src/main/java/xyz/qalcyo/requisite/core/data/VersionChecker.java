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

package xyz.qalcyo.requisite.core.data;

import xyz.qalcyo.json.entities.JsonObject;
import xyz.qalcyo.json.util.JsonApiHelper;
import xyz.qalcyo.requisite.core.IRequisite;
import xyz.qalcyo.requisite.core.util.Multithreading;

import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public class VersionChecker {

    private final String url;
    private BiConsumer<VersionChecker, JsonObject> fetchListener;
    private JsonObject versionObject;

    private IRequisite requisite;

    public VersionChecker(IRequisite requisite, String url, boolean periodicallyFetch) {
        this.url = url;
        this.requisite = requisite;
        if (periodicallyFetch) {
            Multithreading.INSTANCE.schedule(this::fetch, 0, 5, TimeUnit.MINUTES);
        }
    }

    public VersionChecker(IRequisite requisite, String url) {
        this(requisite, url, false);
    }

    public VersionChecker fetch() {
        versionObject = JsonApiHelper.getJsonObject(url);
        fetchListener.accept(this, versionObject);
        return this;
    }

    public String getLatestVersion(String name) {
        return versionObject.get(name).toString();
    }

    public String getLatestVersion() {
        return getLatestVersion("version");
    }

    public String getLatestBeta(String name) {
        return versionObject.get(name).toString();
    }

    public String getLatestBeta() {
        return getLatestBeta("beta");
    }

    public String getDownloadUrl() {
        return versionObject.get("download").toString();
    }

    public String getBetaDownloadUrl() {
        return versionObject.get("beta_download").toString();
    }

    public boolean isLatestVersion(String name, String version) {
        return getLatestVersion(name).matches(version);
    }

    public boolean isLatestVersion(String version) {
        return getLatestVersion().matches(version);
    }

    public boolean isLatestBeta(String name, String version) {
        return getLatestBeta(name).matches(version);
    }

    public boolean isLatestBeta(String version) {
        return getLatestBeta().matches(version);
    }

    public VersionChecker setFetchListener(BiConsumer<VersionChecker, JsonObject> fetchListener) {
        this.fetchListener = fetchListener;
        return this;
    }

}