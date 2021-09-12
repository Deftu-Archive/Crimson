package xyz.qalcyo.requisite.core.networking;

import xyz.qalcyo.json.entities.JsonObject;

public interface IPacketHandler {
    void handle(JsonObject object);
}