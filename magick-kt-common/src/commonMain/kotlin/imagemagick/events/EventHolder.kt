package imagemagick.events

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

// Disclaimer: Code and documentation generated with the help of JetBrains' AI Assistant

/**
 * Class designed to manage the emission of events of generic type `T`.
 *
 * This class implements [CoroutineScope] to allow event emission using coroutines.
 */
@ExperimentalStdlibApi
public class EventHolder<T> : CoroutineScope, AutoCloseable {
    /** The underlying job that can be cancelled to stop all the current operations. */
    private val job = Job()

    /** The context in which the coroutines will be launched. */
    override val coroutineContext: CoroutineContext = job + Dispatchers.Default

    /** The MutableSharedFlow used to hold and send events to subscribers. */
    private val _events = MutableSharedFlow<T>()

    /** The SharedFlow view of the _events property, provides an immutable access to _events. */
    public val events: SharedFlow<T> = _events

    /**
     * Launches a coroutine that emits the provided event to _events.
     *
     * @param event The event of type `T` to be emitted.
     */
    public fun raiseEvent(event: T) {
        launch {
            _events.emit(event)
        }
    }

    /**
     * Emits the provided event to _events.
     *
     * @param event The event of type `T` to be emitted.
     */
    public suspend fun raiseEventAsync(event: T) {
        _events.emit(event)
    }

    /**
     * Cancels the parent job, stopping all ongoing operations and event emissions.
     */
    override fun close() {
        job.cancel()
    }
}
