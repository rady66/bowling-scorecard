# Ten-pin Bowling Game Scorecard Java API

## Background
Information about the game and how the score is calculated:
https://en.wikipedia.org/wiki/Ten-pin_bowling

The project is developed on Java 8.

## How to create an empty score card?
```
ScoreCard card = ScoreCardFactory.createTenPinScoreCard();
```

## How to score a frame (1-9)?
```
int ballOneScore = 9; 
int ballTwoScore = 1; 
scoreCard.score(ballOneScore, ballTwoScore);
```

## How to score the last frame?
```
int ballOneScore = 9; 
int ballTwoScore = 1; 
int ballThreeScore = 10;
scoreCard.score(ballOneScore, ballTwoScore, ballThreeScore);
```

## How to check if a score card is complete?
```
boolean isComplete = scoreCard.isComplete
```

## How to refer the total score?
```
int totalScore = scoreCard.totalScore
```

## How to list score card's frames?
```
scoreCard.frames.stream()
         .forEach(f -> System.out.println(f.isStrike));
```

## How to build the Maven project?
```
mvn clean install
```

## Further Possible Improvements
- More validation (esp. on corner cases)
- Improved memory footprint (`byte`, `short` instead of `int`)
- Concurrency
- Java Doc
- Tests

## Enjoy!
- Author: Radoslav Ivanov