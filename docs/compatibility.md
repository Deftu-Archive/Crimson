# Mod Compatibility
As mod developers,
we all want our mods to be compatible with one another to ensure that everyone can experience what we do.
So to make this easier,
Crimson has it's own inter-transmission API for mods to communicate with each other without complicating things all too much.
If you're interested in making use of it, read [this section](#using-crimsons-inter-transmission-api) of the page.

# Using Crimson's inter-transmission API
The Crimson inter-transmission API is rather simple,
allowing you to store or cache instances of classes or data.
To do so, use the following code:
```java
public void example() {
    Crimson.getInstance().getTransmission().add("EXAMPLE_ID", "Hello, World!");
}
```
The above example adds a `String` item to the transmission storage under the ID `EXAMPLE_ID`.
To fetch from the storage, do as shown below:
```java
public String fetch() {
    return (String) Crimson.getInstance().getTransmission().get("EXAMPLE_ID");
}
```