# Plants in Rows

A clone of the board game, [Mastermind](https://en.wikipedia.org/wiki/Mastermind_(board_game)), developed using Java and the JavaFX framework, created for the purpose of coding practice.

## Table of Contents

* [How to play](#How-to-play)
* [How to run](#How-to-run)
    * [1. IDE](#1.-IDE)
    * [2. Command line](#2.-Command-line)
    * [3. Jar](#3.-Jar)
* [Dependencies](#Dependencies)
* [License](#License)
* [Attribution and licenses of art assets](#Attribution-and-licenses-of-art-assets)

## How to play

You play as a farmer. The goal is to guess the proper type and sequence of plants to grow to maximize your earnings, symbolized by the size of the resulting woodpiles.

You may sow 6 different plants in 4 available positions per turn. Your guess is rated by the amount of logs in again, 4 positions:

- A large woodpile indicates that one of the plants is the desired one, and in the right position.
- A single log means that a plant type is correct, but not its position.
- An absent logpile implies that one plant is definitely not the right type.

You can choose the plants to grow on the right side of the screen with their symbols. Once you are satisfied with your selection, clicking the thumbs up finalizes your choice. In case you change your mind, the thumbs down clears the positions.

You have 10 attempts at finding the proper arrangement to win the game. Keep in mind that a plant may be used more than once in the solution sequence.

## How to run

[OpenJDK 11](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot) and [Maven](https://maven.apache.org/download.cgi) is required! 

Tested on Windows 10.

### 1. IDE

- Import the project in your IDE with the pom.xml to let maven gather the dependencies.
- Compile.
- Use the `App` class as the entry point.

### 2. Command line

From the project root folder in command line execute:
```console
$ mvn compile
```
```console
$ mvn exec:java -Dexec.mainClass=hu.traileddevice.plantsinrows.App
```

### 3. Jar

From the project root folder in command line execute:
```console
$ mvn clean install
```

Change into the `target` folder and execute:
```console
$ java -jar plantsinrows-1.0
```


## Dependencies

* **[JavaFX Controls](https://mvnrepository.com/artifact/org.openjfx/javafx-controls/12.0.2) 12.0.2**
* **[JavaFX Maven Plugin Maven Mojo](https://mvnrepository.com/artifact/org.openjfx/javafx-maven-plugin/0.0.3) 0.0.3**
* **[Apache Maven Compiler Plugin](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-compiler-plugin/3.8.0) 3.8.0**
* **[Apache Maven Assembly Plugin](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-assembly-plugin)**
* **[Project Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.18) 1.18.18**


## License

The source code is available under the **[GNU AGPLv3+](https://www.gnu.org/licenses/agpl-3.0.html)** license.

See the [COPYING](COPYING) and [LICENSE](LICENSE) files for details.

The art assets are from various sources, that come with their own licenses. See below.

## Attribution and licenses of art assets

1. ["ground.png"](src/main/resources/tilesets/ground.png) is a combination of two sources as follows:

    - The image ["ground.png"](src/main/resources/tilesets/ground.png) is partly a derivative of ["grass.png"](https://lpc.opengameart.org/static/lpc-style-guide/assets.html#outdoors) by [Lanea Zimmerman (AKA Sharm)](https://lpc.opengameart.org/static/lpc-style-guide/authors.html#lanea-zimmerman-aka-sharm), used under [CC BY-SA 3.0](https://creativecommons.org/licenses/by-sa/3.0/) and [GNU GPL version 3](https://www.gnu.org/licenses/gpl-3.0.html).

    - The image ["ground.png"](src/main/resources/tilesets/ground.png) is also a derivative of ["[LPC] Farming tilesets, magic animations and UI elements"](https://opengameart.org/content/lpc-farming-tilesets-magic-animations-and-ui-elements) by [Daniel Eddeland](https://opengameart.org/users/daneeklu), used under [CC BY-SA 3.0](https://creativecommons.org/licenses/by-sa/3.0/) and [GNU GPL version 3+](https://www.gnu.org/licenses/gpl-3.0.html).
    
    ["ground.png"](src/main/resources/tilesets/ground.png) is licensed under the [CC BY-SA 3.0](https://creativecommons.org/licenses/by-sa/3.0/) and [GNU GPL version 3+](https://www.gnu.org/licenses/gpl-3.0.html) by David Cereteli. (It is mandatory to give attribution to all previous authors and contributors exactly as they detailed it in their license information.)

2. ["scrollsandblocks.png"](src/main/resources/tilesets/scrollsandblocks.png) is unaltered:

    - The image ["scrollsandblocks.png"](src/main/resources/tilesets/scrollsandblocks.png) is from ["[LPC] Farming tilesets, magic animations and UI elements"](https://opengameart.org/content/lpc-farming-tilesets-magic-animations-and-ui-elements) by [Daniel Eddeland](https://opengameart.org/users/daneeklu), licensed under [CC BY-SA 3.0](https://creativecommons.org/licenses/by-sa/3.0/) and [GNU GPL version 3+](https://www.gnu.org/licenses/gpl-3.0.html).

3. ["plants_and_wood.png"](src/main/resources/tilesets/plants_and_wood.png) is a combination of two sources as follows:

    - The image ["plants_and_wood.png"](src/main/resources/tilesets/plants_and_wood.png) is partly a derivative of ["[LPC] Farming tilesets, magic animations and UI elements"](https://opengameart.org/content/lpc-farming-tilesets-magic-animations-and-ui-elements) by [Daniel Eddeland](https://opengameart.org/users/daneeklu), used under [CC BY-SA 3.0](https://creativecommons.org/licenses/by-sa/3.0/) and [GNU GPL version 3+](https://www.gnu.org/licenses/gpl-3.0.html).

    - The image ["plants_and_wood.png"](src/main/resources/tilesets/plants_and_wood.png) is also a derivative of ["[LPC] Crops"](https://opengameart.org/content/lpc-crops) by [bluecarrot16](https://opengameart.org/users/bluecarrot16),
    [Daniel Eddeland (daneeklu)](https://opengameart.org/content/rpg-item-set), [Joshua Taylor](https://opengameart.org/content/fruit-and-veggie-inventory), [Richard Kettering (Jetrel)](https://opengameart.org/content/rpg-item-set), and commissioned by [castelonia](https://opengameart.org/users/castelonia), used under [CC BY-SA 3.0+](https://creativecommons.org/licenses/by-sa/3.0/) or [GNU GPL version 3+](https://www.gnu.org/licenses/gpl-3.0.html). (See [CREDITS-crops.txt](src/main/resources/tilesets/CREDITS-crops.txt). You MUST include all information in this file!)
    
    ["plants_and_wood.png"](src/main/resources/tilesets/plants_and_wood.png) is licensed under the [CC BY-SA 3.0+](https://creativecommons.org/licenses/by-sa/3.0/) and [GNU GPL version 3+](https://www.gnu.org/licenses/gpl-3.0.html) by David Cereteli. (It is mandatory to give attribution to all previous authors and contributors exactly as they detailed it in their license information.)

4. ["icons.png"](src/main/resources/tilesets/icons.png) is a combination of eleven sources as follows:

    - The image ["icons.png"](src/main/resources/tilesets/icons.png) is partly a derivative of ["New born icon"](https://game-icons.net/1x1/delapouite/new-born.html) by [Delapouite](https://delapouite.com/), used under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/).

    - The image ["icons.png"](src/main/resources/tilesets/icons.png) is also a derivative of ["Chicken icon"](https://game-icons.net/1x1/delapouite/chicken.html) by [Delapouite](https://delapouite.com/), used under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/).

    - The image ["icons.png"](src/main/resources/tilesets/icons.png) is also a derivative of ["Pumpkin icon"](https://game-icons.net/1x1/delapouite/pumpkin.html) by [Delapouite](https://delapouite.com/), used under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/).

    - The image ["icons.png"](src/main/resources/tilesets/icons.png) is also a derivative of ["Watermelon icon"](https://game-icons.net/1x1/delapouite/watermelon.html) by [Delapouite](https://delapouite.com/), used under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/).

    - The image ["icons.png"](src/main/resources/tilesets/icons.png) is also a derivative of ["Bell pepper icon"](https://game-icons.net/1x1/delapouite/bell-pepper.html) by [Delapouite](https://delapouite.com/), used under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/).

    - The image ["icons.png"](src/main/resources/tilesets/icons.png) is also a derivative of ["Tomato icon"](https://game-icons.net/1x1/delapouite/tomato.html) by [Delapouite](https://delapouite.com/), used under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/).

    - The image ["icons.png"](src/main/resources/tilesets/icons.png) is also a derivative of ["Aubergine icon"](https://game-icons.net/1x1/lorc/aubergine.html) by [Lorc](https://lorcblog.blogspot.com/), used under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/).

    - The image ["icons.png"](src/main/resources/tilesets/icons.png) is also a derivative of ["Cabbage icon"](https://game-icons.net/1x1/delapouite/cabbage.html) by [Delapouite](https://delapouite.com/), used under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/).

    - The image ["icons.png"](src/main/resources/tilesets/icons.png) is also a derivative of ["Thumb up icon"](https://game-icons.net/1x1/delapouite/thumb-up.html) by [Delapouite](https://delapouite.com/), used under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/).

    - The image ["icons.png"](src/main/resources/tilesets/icons.png) is also a derivative of ["Thumb down icon"](https://game-icons.net/1x1/delapouite/thumb-down.html) by [Delapouite](https://delapouite.com/), used under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/).

    - The image ["icons.png"](src/main/resources/tilesets/icons.png) is also a derivative of ["Scroll unfurled icon"](https://game-icons.net/1x1/lorc/scroll-unfurled.html) by [Lorc](https://lorcblog.blogspot.com/), used under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/).
    
    ["icons.png"](src/main/resources/tilesets/icons.png) is licensed under the [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/) by David Cereteli. (It is mandatory to give attribution to all previous authors and contributors exactly as they detailed it in their license information.)
    
5. ["farmer.png"](src/main/resources/tilesets/farmer.png) is unaltered:

    - The image ["farmer.png"](src/main/resources/tilesets/farmer.png) is the ["Farmer icon"](https://game-icons.net/1x1/delapouite/farmer.html) by [Delapouite](https://delapouite.com/), licensed under [CC BY 3.0](https://creativecommons.org/licenses/by/3.0/).