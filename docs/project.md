# Crimson Dependency
Crimson has to be added to your mod's development environment for you to take advantage of it's APIs. To do so, add these lines somewhere within your project's `build.gradle` file:
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
If you don't know where to find the latest version of Crimson, it should be visible in the [official documentation on our site.](https://docs.qalcyo.xyz/requisite/)