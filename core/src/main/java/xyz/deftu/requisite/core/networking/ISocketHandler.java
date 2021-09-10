package xyz.deftu.requisite.core.networking;

public interface ISocketHandler {
    void initialize(RequisiteClientSocket socket);
    void connect(RequisiteClientSocket socket);
    void disconnect(RequisiteClientSocket socket);
}