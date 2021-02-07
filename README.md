## Number guessing game
This is a simple game, what the user can start, and play using the provided REST URLs.

## Installation

JAVA_HOME environment variable is needed to start the application.
Git executable needs to be on the PATH to check out the code.

```
# clone the project code to the local directory
git clone https://github.com/atombor/numberguess.git

#build the application with the built-in maven wrapper
cd numberguess
mvnw clean package

#start the application
java -jar ./target/numberguess-0.0.1-SNAPSHOT.jar
```

## Usage
The game can be started on:  
http://localhost:8080/startGame?name=<playername>
```
{
"playerId": 1,
"playerName": "Peter",
"startTime": "2021-02-07T22:39:11.037617",
"message": "Welcome to the Number Guess Game, Peter!This game is about to guess a number between 1 and 100Your number is ready for guessing! Good Luck!"
}
```

The game can be played on the followin URL, using the previously returned playerId:  
http://localhost:8080/guessTheNumber?playerId=<playerId>&guessedNumber=<actual guess>
```
{
  "guessResultStatus": "LOWER",
  "guessCount": 1,
  "message": "Your guess: 21, is lower, than the expected one.",
  "secondsUntilSuccess": -1
}
```

The game is finished when the guessResultStatus field returns 'CATCH':
```
{
"guessResultStatus": "CATCH",
"guessCount": 10,
"message": "Your guess: 64 is correct!",
"secondsUntilSuccess": 198
}
```