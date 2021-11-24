# Crimson Mod Integration
Crimson provides an extensive API for inter-mod communication and ease-of-use features for the end user, such as it's custom mod list. Mods can use these APIs to store data, access data from other mods and [give important info to the user](#providing-important-information).

If you'd like your mod to be visible in Crimson's mod API, [check this out.](#implementing-Crimson-integration-api)

# Implementing Crimson's Integration API
Making your mod available with all other mods using Crimson is extremely simple. When adding properties to your mod's manifest, add `ModClass` as a property directing to your class in package form (`com.example.ExampleMod`).

For this to work effectively, your mod class must implement the `IMod` class. To learn more about what this class, [refer to this section of this page.](#providing-important-information)

# Providing Important Information
Inside of Crimson's mod list is a small page for every mod installed that has registered itself as a Crimson mod using the [method shown here.](#implementing-Crimson-integration-api) As shown in the section mentioned, your mod needs to implement the `IMod` interface. This interface's only purpose is to give Crimson important info of your mod, such as it's name, version, main command, etc. It's vital to provide this information for a better user experience.