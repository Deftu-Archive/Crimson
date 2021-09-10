package xyz.deftu.requisite.core.networking;

import java.util.UUID;

public interface ISocketHelper {
    UUID getUuid();
    String getName();
    void chat(String message);
}