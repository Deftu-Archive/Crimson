package xyz.qalcyo.requisite.core.networking;

import xyz.qalcyo.json.entities.JsonObject;

public abstract class BasePacket {

    private final String type;
    private final JsonObject data;
    private final boolean mass;

    public BasePacket(String type, boolean mass) {
        this.type = type;
        this.data = new JsonObject();
        this.mass = mass;
    }

    public BasePacket(String type) {
        this(type, false);
    }

    public abstract void send(RequisiteClientSocket socket, JsonObject data);
    public abstract void receive(RequisiteClientSocket socket, JsonObject packet, JsonObject data);

    final JsonObject getData() {
        return data;
    }

    public final String getType() {
        return type;
    }

    public final boolean isMass() {
        return mass;
    }

    public final JsonObject jsonify() {
        JsonObject value = new JsonObject();
        value.add("type", type);
        value.add("data", data);
        return value;
    }

}