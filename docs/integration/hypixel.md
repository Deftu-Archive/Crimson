# Crimson Hypixel Integration
Hypixel is _the_ largest Minecraft server out there as of writing this, and for this reason a lot of mods are being made just to provide Quality of Life features for Hypixel players. To aide mod developers in this, we've added simple Hypixel integration into Crimson in the form of [Locraw](#what-is-locraw) fetching and basic Hypixel API interaction.

# What is locraw?
Locraw is Hypixel's system for providing world/lobby info for mods, this provides the map, server ID, game mode and game type. Locraw is especially useful for mods that need the user to be playing a specific Hypixel game mode to function, such as "Hypixel SkyBlock" mods.

# How to utilize it.
It's simple to use Crimson's Hypixel API. There are two ways to easily retrieve locraw, and those are to use the `LocrawReceivedEvent` and `HypixelLocrawManager#getLocraw`. It's usually better to use the event, though in specific situations you may use the getter. `LocrawReceivedEvent` is posted to Crimson's event bus every time a locraw is requested and received. As an example,
```java
@SubscribeEvent
private void onLocrawReceived(LocrawReceivedEvent event) {
    this.gameType = event.locraw.getGameType();
}
```