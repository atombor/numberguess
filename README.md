### Number guessing game
This application is a game, where the user can start a game, and play using the provided REST URLs.

### Installing

JAVA_HOME is  pre-requisite to start the application.


```
export JAVA_HOME=~/work/binary/jdk-11.0.10

mvn clean package
java -jar ./target/numberguess-0.0.1-SNAPSHOT.jar


http://localhost:8080/startGame?name=<playername>

http://localhost:8080/guessTheNumber?playerId=<playerId>&guessedNumber=<actual guess>
``