package pl.edu.agh.distributed.zookeeper

import org.apache.zookeeper.AddWatchMode
import org.apache.zookeeper.Watcher.Event.EventType
import org.apache.zookeeper.ZooKeeper

private const val NODE_NAME = "/a"

enum class Event {
    Added, Deleted
}

fun zk(
    zk: ZooKeeper,
    onEvent: (String, Event) -> Unit
) {
    val extAppBin = System.getenv("ZK_EXT_APP") ?: "glxgears"
    var proc: Process? = null

    fun runExtApp() {
        proc?.destroy()
        proc = Runtime.getRuntime().exec(extAppBin)
    }

    fun closeExtApp() {
        proc?.destroy()
        proc = null
    }

    fun rcGetChildren(node: String) {
        val children = zk.getChildren(node, false)
        children.forEach { onEvent("$node/$it", Event.Added) }
        children.forEach { rcGetChildren("$node/$it") }
    }

    zk.exists(NODE_NAME, false)?.let {
        onEvent(NODE_NAME, Event.Added)
        rcGetChildren(NODE_NAME)
    }

    zk.addWatch(NODE_NAME, { watchEvent ->
        val path = watchEvent.path

        println(watchEvent)

        when (watchEvent.type) {
            EventType.NodeCreated -> {
                if (path == NODE_NAME) runExtApp()
                onEvent(path, Event.Added)
            }
            EventType.NodeDeleted -> {
                if (path == NODE_NAME) closeExtApp()
                onEvent(path, Event.Deleted)
            }
            else -> {}
        }
    }, AddWatchMode.PERSISTENT_RECURSIVE)
}