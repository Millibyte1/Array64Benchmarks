Microbenchmarks for the [Array64 library](https://github.com/Millibyte1/Array64) for huge arrays on the JVM.

- [Development](#development)
- [Results](#results)
- [Interpretation](#interpretation)
- [Specs](#specs)
- [Running the benchmarks yourself](#running-the-benchmarks-yourself)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>

## Development 
These benchmarks were developed using OpenJDK's Java Microbenchmarking Harness.

These benchmarks compare the performance of Array64 against FastUtil's BigArrays module, a lower-level tool for manipulating
2D arrays in Java used in scientific and high-performance computing applications (and internally by Array64 for some operations).

The Maven pom.xml file for this project is a good template for anyone looking to write JMH microbenchmarks in Kotlin.

## Results
| Benchmark                                               | Mode   | Cnt | Score    | Error     | Units  |
| ------------------------------------------------------- | ------ | --- | -------- | --------- | ------ |
| FastByteArray64Benchmarks.sequentialAccessViaForEach    | avgt   | 25  | 1280.953 | ± 11.985  | ms/op  |
| FastByteArray64Benchmarks.sequentialAccessViaIterator   | avgt   | 25  | 1352.156 | ± 13.178  | ms/op  |
| FastUtilByteArrayBenchmarks.sequentialAccess            | avgt   | 25  | 1003.542 | ± 11.602  | ms/op  |
| FastObjectArray64Benchmarks.sequentialAccessViaForEach  | avgt   | 25  | 1287.035 | ± 9.112   | ms/op  |
| FastObjectArray64Benchmarks.sequentialAccessViaIterator | avgt   | 25  | 1346.005 | ± 12.665  | ms/op  |
| FastUtilObjectArrayBenchmarks.sequentialAccess          | avgt   | 25  | 1009.380 | ± 11.395  | ms/op  |
| FastByteArray64Benchmarks.randomAccess                  | thrpt  | 25  | 7515.261 | ± 33.293  | ops/ms |
| FastUtilByteArrayBenchmarks.randomAccess                | thrpt  | 25  | 7470.478 | ± 44.760  | ops/ms |
| FastObjectArray64Benchmarks.randomAccess                | thrpt  | 25  | 6715.841 | ± 114.816 | ops/ms |
| FastUtilObjectArrayBenchmarks.randomAccess              | thrpt  | 25  | 6673.575 | ± 53.757  | ops/ms |

These results are current as of Array64 v1.0.1.

The raw console output from running the benchmarks is available in raw/benchmarks-output.txt.

## Interpretation
For random array element access, the performance difference between Array64 and FastUtil is negligible.

For sequential array element access, there's around a 25% overhead to using Array64 over FastUtil.

## Specs
These results were obtained on a machine with a Ryzen 7 3700x CPU and 64GB of RAM.

## Running the benchmarks yourself
1. Ensure Java and Maven are installed, and that you have at least 16GB RAM available for the application
2. Clone this repository
3. From the root directory of the project (with the pom.xml), run "mvn install package"
4. Execute the JMH-generated executable jar file with the command "java -jar ./target/benchmarks.jar"
5. Do something else for a few hours
