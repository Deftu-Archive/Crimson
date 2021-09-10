package xyz.deftu.requisite.core.networking;

import xyz.deftu.json.entities.JsonObject;

public interface IPacketHandler {
    void handle(JsonObject object);
}