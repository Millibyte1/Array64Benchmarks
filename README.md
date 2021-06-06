Microbenchmarks for the [Array64 library](https://github.com/Millibyte1/Array64) for huge arrays on the JVM.

## Development 
These benchmarks were developed using OpenJDK's Java Microbenchmarking Harness.

These benchmarks compare the performance of Array64 against FastUtil's BigArrays module, a lower-level tool for manipulating
2D arrays in Java used in scientific and high-performance computing applications (and internally by Array64 for some operations).

The pom.xml for this project is a good template for anyone looking to write JMH microbenchmarks in Kotlin.

## Results

### Interpretation
The performance difference of random element access between the two libraries is negligible both for object and primitive arrays.

FastUtil outperforms Array64 at sequential element access by around TODO%. Performance-critical applications should keep this in mind.

### Summary Results
TODO

### Verbose results
The raw console output from running the benchmarks is available in raw/benchmarks-output.txt. 



