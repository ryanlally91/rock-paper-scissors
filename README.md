# rock-paper-scissors Spring Boot API with Angular UI

 
A Spring-Boot + Angular Application
The program contains two types of players, one always selects randomly and the other always selects rock.

Each instance of the application running (on seperate browser windows) will maintain its own session ID and keep track of games associated with that ID in a table. There is also the ability to clear all games associated with this session which in turn, clears the table

Below this table, there is global record of all games/wins/draws across all inctances of the application running. This global information cannot be cleared and will persist for the lifcycle of the app.


![screenshot-2](https://user-images.githubusercontent.com/32078235/194777817-69b83092-2161-4570-8f54-49fd25f1f850.jpg)


## Getting Started

To install this example application, both spring boot and angular projects are cloned using the followin following commands:

```bash
git clone https://github.com/ryanlally91/rock-paper-scissors.git
```

This will get a copy of the project installed locally. To install all of its dependencies and start each app, follow the instructions below.

To run the server, import the `spring-boot-rock-paper-scissors` folder into Eclipse:
 
```bash
clean install
then simply run as java application
```

To run the client, cd into the `angular-rock-paper-scissors-app` folder, open in vs code:
 
```bash
npm install
ng serve --live-reload   
```

## Help
