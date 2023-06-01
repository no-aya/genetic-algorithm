# Genetic Algothim Implementation 

This is a demo emplementation of the genetic algorithm in java. The algorithm is used to solve 2 problems :
- Finding the right serie of binary numbers that will give the maximum value of fitness function.
- Finding the right sentence that will give the maximum value of fitness function.

## Sequencial implementation
- [APP 1 : Genetic Algorithm](./APP1-GeneticAlgorithm/)

Finding the right serie of binary numbers that will give the maximum value of fitness function. 

Solution : 
```
The best solution is : 
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
The best fitness is :
10.0
```

- [APP 2 : Genetic Algorithm with Alphabet](./APP2-GeneticAlgorithm%20-Alphabet/)

Finding the right sentence that will give the maximum value of fitness function.

Solution : 
```
The best solution is :
[Bonjour BDCC]
The best fitness is :
12.0
```

## MAS implementation [Island model]

The island model is a distributed version of the genetic algorithm. 
We are trying to solve the same problems as the sequencial implementation but this time we are using a distributed version of the genetic algorithm.

These implementations uses JADE framework to implement the MAS architecture. 
[More about JADE](http://jade.tilab.com/)

- [APP 3 : Genetic Algorithm Island](./APP3-GeneticAlgorithmIsland/)

Finding the right serie of binary numbers that will give the maximum value of fitness function.

Solution : 
```
The best solution is : 
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
The best fitness is :
10.0
```

- [APP 4 : Genetic Algorithm Island with Alphabet](./APP4-GeneticAlgorithmIsland%20-%20Alphabet/)

Solution : 
```
The best solution is :
[Bonjour BDCC]
The best fitness is :
12.0
```



