package xyz.qalcyo.requisite.core.data

import xyz.qalcyo.json.entities.JsonObject
import xyz.qalcyo.json.util.JsonApiHelper
import xyz.qalcyo.requisite.core.IRequisite
import xyz.qalcyo.requisite.core.util.Multithreading.schedule
import java.util.concurrent.TimeUnit
import java.util.function.BiConsumer

class VersionChecker @JvmOverloads constructor(
    private val requisite: IRequisite,
    private val url: String,
    periodicallyFetch: Boolean = false
) {
    private var fetchListener: BiConsumer<VersionChecker, JsonObject?>? = null
    private var versionObject: JsonObject? = null
    fun fetch(): VersionChecker {
        versionObject = JsonApiHelper.getJsonObject(url)
        fetchListener!!.accept(this, versionObject)
        return this
    }

    fun getLatestVersion(name: String?): String {
        return versionObject!![name].toString()
    }

    val latestVersion: String
        get() = getLatestVersion("version")

    fun getLatestBeta(name: String?): String {
        return versionObject!![name].toString()
    }

    val latestBeta: String
        get() = getLatestBeta("beta")
    val downloadUrl: String
        get() = versionObject!!["download"].toString()
    val betaDownloadUrl: String
        get() = versionObject!!["beta_download"].toString()

    fun isLatestVersion(name: String, version: String): Boolean {
        return getLatestVersion(name).matches(version.toRegex())
    }

    fun isLatestVersion(version: String): Boolean {
        return latestVersion.matches(version.toRegex())
    }

    fun isLatestBeta(name: String, version: String): Boolean {
        return getLatestBeta(name).matches(version.toRegex())
    }

    fun isLatestBeta(version: String): Boolean {
        return latestBeta.matches(version.toRegex())
    }

    fun setFetchListener(fetchListener: BiConsumer<VersionChecker, JsonObject?>?): VersionChecker {
        this.fetchListener = fetchListener
        return this
    }

    init {
        if (periodicallyFetch) {
            schedule({ fetch() }, 0, 5, TimeUnit.MINUTES)
        }
    }
}