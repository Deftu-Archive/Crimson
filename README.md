# Requisite
## The ultimate Minecraft modding library.
[![forthebadge](https://forthebadge.com/images/badges/makes-people-smile.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/fixed-bugs.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/check-it-out.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/for-you.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/open-source.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://forthebadge.com)

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
    implementation('xyz.deftu.requisite:Requisite-MCVERSION:VERSION')
    shade('xyz.deftu.requisite:RequisiteLaunchwrapper:VERSION')
}
```