package io.github.millibyte1.array64.benchmarks

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import java.util.concurrent.TimeUnit

open class FastUtilByteArrayBenchmarks {

    @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.MILLISECONDS)
    fun sequentialAccess() {

    }
    @Benchmark @BenchmarkMode(Mode.All) @OutputTimeUnit(TimeUnit.MILLISECONDS)
    fun randomAccess() {

    }
}