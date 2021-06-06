package io.github.millibyte1.array64.benchmarks

import it.unimi.dsi.fastutil.BigArrays

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

import kotlin.properties.Delegates
import kotlin.random.Random

open class FastUtilObjectArrayBenchmarks {

    @State(Scope.Thread)
    open class BenchmarkState {

        private val random: Random = Random(0)
        val array: Array<Array<Object>> = createArray(268435456)
        var randomIndex by Delegates.notNull<Long>()

        @Setup(Level.Invocation)
        fun performSetup() {
            randomIndex = random.nextLong(268435456)
        }

        companion object {
            @JvmStatic
            private fun createArray(size: Long): Array<Array<Object>> {
                //calculates the number of complete inner arrays and the size of the incomplete last inner array
                val fullArrays = (size / BigArrays.SEGMENT_SIZE).toInt()
                val innerSize = (size % BigArrays.SEGMENT_SIZE).toInt()
                //allocates the array
                return (
                    if (innerSize == 0) Array(fullArrays) { Array(BigArrays.SEGMENT_SIZE) { Object() } }
                    else Array(fullArrays + 1) { i -> if (i == fullArrays) Array(innerSize) { Object() } else Array(BigArrays.SEGMENT_SIZE) { Object() } }
                )
            }
        }
    }

    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MILLISECONDS)
    fun sequentialAccessWithoutIndex(state: BenchmarkState, blackhole: Blackhole) {
        for(segment in state.array.indices) {
            val inner = state.array[segment]
            for(displacement in inner.indices) {
                blackhole.consume(inner[displacement])
            }
        }
    }
    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MILLISECONDS)
    fun sequentialAccessWithIndex(state: BenchmarkState, blackhole: Blackhole) {
        var index = 0L
        for(segment in state.array.indices) {
            val inner = state.array[segment]
            for(displacement in inner.indices) {
                blackhole.consume(inner[displacement])
                index++
            }
        }
        blackhole.consume(index)
    }
    @Benchmark @BenchmarkMode(Mode.Throughput) @OutputTimeUnit(TimeUnit.MILLISECONDS)
    fun randomAccess(state: BenchmarkState, blackhole: Blackhole) {
        blackhole.consume(BigArrays.get(state.array, state.randomIndex))
    }
}