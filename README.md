# Crimson
## The ultimate Minecraft modding library.

## What is it?
Crimson is a library for making Minecraft mods, including utilities for most needs and other commonly used components!

## How do I use it?
Inside your project's `build.gradle` file, add these fields.
```gradle
repositories {
    maven {
        name = 'Crimson'
        url = 'https://maven.qalcyo.xyz/repository/maven-public/'
    }
}

dependencies {
    implementation('xyz.qalcyo.crimson:Crimson:VERSION')
    shade('xyz.qalcyo.crimson:CrimsonInstaller:VERSION')
}
```