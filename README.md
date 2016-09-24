# Multidimensional-Matrix-Multiplication
A java program that can multiply two n-dimensional matrices.

##Input format:
###Entering Dimensions of a Matrix: 
n space separated integers, where n is the dimensions
e.g. 
* 2 3 4 {for a 3d matrix}
* 6 3 7 2 4 {for a 5d matrix}

###Entering values of n-D matrix from innermost dimension to outermost: 
Enter the values of each element of the matrix from innermost dimension (right most) to outermost (left most)
e.g.
for the 3d matrix with dimensions 2x3x4 i.e it contains 24 elements,
the values are to be inserted in the following order 
```
[1,1,1] [1,1,2] [1,1,3] [1,1,4]
[1,2,1] [1,2,2] [1,2,3] [1,2,4]
[1,3,1] [1,3,2] [1,3,3] [1,3,4]
[2,1,1] [2,1,2] [2,1,3] [2,1,4]
[2,2,1] [2,2,2] [2,2,3] [2,2,4]
[2,3,1] [2,3,2] [2,3,3] [2,3,4]
```

###Entering the dimensions to multiply:
Enter 2 space separated integers denoting the index of the dimensions to multiply (its 1-ordered and not 0-ordered)
e.g.
for multiplying two 3d matrices we can use
* 1 2 {for multiplying first dimension of Matrix A and second dimension of Matrix B}
* 1 3 {for multiplying first dimension of Matrix A and third dimension of Matrix B}
* 2 1 {for multiplying second dimension of Matrix A and first dimension of Matrix B}
* 2 3 {for multiplying second dimension of Matrix A and third dimension of Matrix B}
* 3 1 {for multiplying third dimension of Matrix A and first dimension of Matrix B}
* 3 2 {for multiplying third dimension of Matrix A and second dimension of Matrix B}

Note: we cannot multiply along the same dimension i.e (1 1) (2 2) and (3 3)

##Output format:
The output format may be a bit difficult to understand at first but it is the best I could do to represent an multidimensional result
consider the following example:
```
Example 1:

Dimension of Matrix A : 2 2
Elements of A:
1 2 
3 4

Dimension of Matrix B : 2 2
Elements of B:
1 2
3 4

Multiplying along: 1 2 {first dimension of Matrix A and second dimension of Matrix B}

Dimension of Resultant Matrix C: 2 2
Elements of C:
7 10        [0,0] [0,1]
15 22       [1,0] [1,1]

The elements above are outputted in the program as follows:
[[7, 10], [15, 22]]

i.e. the brackets correspond to separate dimensions from innermost to outermost
```
```
Example 2:
Dimension of Matrix A: 2 3 4
Elements of A:
1 2 3 4
1 2 3 4
1 2 3 4
1 2 3 4
1 2 3 4
1 2 3 4

Dimensions of Matrix B: 4 3 2
Elements of B:
5 6
5 6
5 6
5 6
5 6
5 6
5 6
5 6
5 6
5 6
5 6
5 6

Multiplying along: 1 3 {first dimension of Matrix A and third dimension of Matrix B}

Dimension of Resultant Matrix C: 4 3 4
Elements of C:
[[[11, 22, 33, 44], [11, 22, 33, 44], [11, 22, 33, 44]], [[11, 22, 33, 44], [11, 22, 33, 44], [11, 22, 33, 44]], [[11, 22, 33, 44], [11, 22, 33, 44], [11, 22, 33, 44]], [[11, 22, 33, 44], [11, 22, 33, 44], [11, 22, 33, 44]]]
```




