/*
 * Requisite - Minecraft library mod
 *  Copyright (C) 2021 Qalcyo
 *
 * Requisite is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Requisite is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Requisite. If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.qalcyo.requisite.core.util

import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger

class Multithreading {

    private val threadCount = AtomicInteger(0)
    private val executor = ThreadPoolExecutor(
        50, 50, 0L, TimeUnit.MILLISECONDS, LinkedBlockingQueue()
    ) { r: Runnable? ->
        Thread(
            r,
            String.format("Requisite thread %s", threadCount.incrementAndGet())
        )
    }
    private val runnableExecutor: ScheduledExecutorService = ScheduledThreadPoolExecutor(6)

    /**
     * @param runnable The code to run asynchronously.
     */
    fun runAsync(runnable: Runnable) =
        executor.execute(runnable)

    /**
     * @param runnable The runnable code to be ran.
     * @param delay    The delay before running.
     * @param timeUnit The time unit of the delay.
     */
    fun schedule(runnable: Runnable, delay: Long, timeUnit: TimeUnit): ScheduledFuture<*> =
        runnableExecutor.schedule(runnable, delay, timeUnit)

    /**
     * @param runnable   The runnable code to be ran.
     * @param startDelay The initial delay before running the first time.
     * @param delay      The delay before running.
     * @param timeUnit   The time unit of the delay.
     */
    fun schedule(runnable: Runnable, startDelay: Long, delay: Long, timeUnit: TimeUnit): ScheduledFuture<*> =
        runnableExecutor.scheduleAtFixedRate(runnable, startDelay, delay, timeUnit)

    /**
     * @param runnable The code to submit.
     */
    fun submit(runnable: Runnable): Future<*> =
        executor.submit(runnable)

}