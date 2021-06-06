package io.github.millibyte1.array64.benchmarks

import io.github.millibyte1.array64.FastByteArray64

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

import kotlin.properties.Delegates
import kotlin.random.Random

open class FastByteArray64Benchmarks {

    @State(Scope.Thread)
    open class BenchmarkState {

        private val random: Random = Random(0)
        val array: FastByteArray64 = FastByteArray64(268435456)
        var randomIndex by Delegates.notNull<Long>()

        @Setup(Level.Invocation)
        fun performSetup() {
            randomIndex = random.nextLong(array.size)
        }
    }

    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MILLISECONDS)
    fun sequentialAccessViaForEach(state: BenchmarkState, blackhole: Blackhole) {
        state.array.forEach { e -> blackhole.consume(e) }
    }

    @Benchmark @BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MILLISECONDS)
    fun sequentialAccessViaIterator(state: BenchmarkState, blackhole: Blackhole) {
        state.array.iterator().forEachRemaining { e -> blackhole.consume(e) }
    }

    @Benchmark @BenchmarkMode(Mode.Throughput) @OutputTimeUnit(TimeUnit.MILLISECONDS)
    fun randomAccess(state: BenchmarkState, blackhole: Blackhole) {
        blackhole.consume(state.array[state.randomIndex])
    }
}