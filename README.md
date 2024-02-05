# Cursor Game Demo
This repository showcases a Java 20 game demo built with Maven, featuring graphics and GUI elements handled through Java's JFrame. The game loop engine takes inspiration from various online tutorials but is still developed from scratch.

![image](https://github.com/Gabriel-Wright/CursorDemoGame/assets/54595455/7d068006-c0bc-460c-b347-dba8af85c90c)

## Current Features
- Cursor and entity collision! The cursor is your weapon, use it to protect the player from other entities! The cursor cannot pass through walls, so you must multitask to control both the player object (the square) and the player cursor (the circle) to survive.
- Entity pathfinding, using A* search algorithm
- Spawn events occur in waves, with a score based system based on how long you survive.

## Milestones 🎯
- Display and control player character ✅
- Display map ✅
- Add collisions between gameObjects and level tiles ✅
- Create GameObjectHandler to resolve all gameObject updates, including collisions between gameObjects ✅
- Display objects ✅
- Add background Task events, including ui and sound ✅
- Add cursor "shooting" ✅
- Add cursor tile and gameObject collision ✅
- Handle loading of gameStates and switching between gameStates ✅
- Menu and options settings ✅
- Add scoring system ✅
- WaveManager system to trigger spawning of events and entities ✅
- Improved UI, and sound indicators for when trigger events need to be completed i.e. a countdown effect
- Balancing and more custom events
- Better level loading system - refactor main game loop to inject constants instead of instantiating within manager classes for tiles, level etc.
- Game Objects that change player stats, give boost or kill multiple enemies
- Random object spawning
- Improved visuals, better assets
- Multiple entities
- Have the camera move the game to a new part of the level

## Getting started
- Clone the repository

- Build the project using Maven:
mvn clean install

- Run the created jar using java
java -jar (inputted jar name).jar

## Usage
- Use your mouse by holding left mouse button to attack enemies.

![image](https://github.com/Gabriel-Wright/CursorDemoGame/assets/54595455/ef640cb5-f9ec-48fd-bf7f-352c7e2fdaba)
- Complete tasks with your mouse and player character.

![image](https://github.com/Gabriel-Wright/CursorDemoGame/assets/54595455/de3e43f5-9b5e-40d8-94c0-faf7d5fca1b5)

A purple block must be turned off by a player, and a pink by the cursor.

![image](https://github.com/Gabriel-Wright/CursorDemoGame/assets/54595455/17d74ab9-e209-43a2-9343-7572bca1d687)

- Avoid hitting red zones with your cursor, this causes you to lose charge needed to kill threatening entities

![image](https://github.com/Gabriel-Wright/CursorDemoGame/assets/54595455/6cd25b19-be39-4436-b4c8-fe90390d7127)

## License
This project is licensed under the [MIT License](https://choosealicense.com/licenses/mit/#)
## Acknowledgements
- [RyiSnow's Java Game Development Youtube Series](https://www.youtube.com/playlist?list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq) followed at the start for initial gameLoop

- [vanZeben Java Game Engine Development Series](https://www.youtube.com/watch?v=VE7ezYCTPe4&list=PL8CAB66181A502179) followed level and tile system

- [CodeNMore 2d Game Programming Series](https://www.youtube.com/watch?v=dEKs-3GhVKQ&list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ) followed for game states and eventManager

- [Article on gameCollisions](https://www.gamedev.net/tutorials/_/technical/game-programming/the-guide-to-implementing-2d-platformers-r2936/)

- [Article on 2d spatial hashmap](https://www.gamedev.net/tutorials/programming/general-and-gameplay-programming/spatial-hashing-r2697/)
