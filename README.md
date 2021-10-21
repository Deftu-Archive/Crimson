# Requisite
## The ultimate Minecraft modding library.
[![Crowdin](https://badges.crowdin.net/requisite/localized.svg)](https://crowdin.com/project/requisite)

## What is it?
Requisite is a library for making Minecraft mods, including utilities for most needs and other commonly used components!

## How do I use it?
Inside your project's `build.gradle` file, add these fields.
```gradle
repositories {
    maven {
        name = 'Requisite'
        url = 'http://maven.deftu.xyz/repository/maven-public/'
        allowInsecureProtocol = true
    }
}

dependencies {
    implementation('xyz.qalcyo.requisite:Requisite:VERSION')
    shade('xyz.qalcyo.requisite:RequisiteInstaller:VERSION')
}
```