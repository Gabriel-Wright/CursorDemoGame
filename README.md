# Cursor Game Demo
This repository showcases a Java 20 game demo built with Maven, featuring graphics and GUI elements handled through Java's JFrame. The game loop engine takes inspiration from various online tutorials but is still developed from scratch.

![image](https://github.com/Gabriel-Wright/CursorDemoGame/assets/54595455/7d068006-c0bc-460c-b347-dba8af85c90c)

## Current Features
- Cursor and entity collision! The cursor is your weapon, use it to protect the player from other entities! The cursor cannot pass through walls, so you must multitask to control both the player object (the square) and the player cursor (the circle) to survive.
![image](https://github.com/Gabriel-Wright/CursorDemoGame/assets/54595455/87e94bdb-1e7f-4967-a7e3-654dc0e9d4db)
- Entity pathfinding, using A* search algorithm
![image](https://github.com/Gabriel-Wright/CursorDemoGame/assets/54595455/0f181012-5e82-4b3b-8414-8731c0a8aae1)
- Spawn events occur in waves, with a score based system based on how long you survive.
![image](https://github.com/Gabriel-Wright/CursorDemoGame/assets/54595455/7ab0883e-16a7-4eac-bce6-a3a750176094)

## Milestones 🎯

## Getting started
- Clone the repository:
git clone https://github.com/your-username/your-repo.git

- Build the project using Maven:
mvn clean install

- Run the game:
java -jar target/your-game.jar

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

## Acknowledgements
- [RyiSnow's Java Game Development Youtube Series](https://www.youtube.com/playlist?list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq) followed at the start for initial gameLoop

- [vanZeben Java Game Engine Development Series](https://www.youtube.com/watch?v=VE7ezYCTPe4&list=PL8CAB66181A502179) followed level and tile system

- [CodeNMore 2d Game Programming Series](https://www.youtube.com/watch?v=dEKs-3GhVKQ&list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ) followed for game states and eventManager

- [Article on gameCollisions](https://www.gamedev.net/tutorials/_/technical/game-programming/the-guide-to-implementing-2d-platformers-r2936/)

- [Article on 2d spatial hashmap](https://www.gamedev.net/tutorials/programming/general-and-gameplay-programming/spatial-hashing-r2697/)
