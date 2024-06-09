package pl.edu.agh.distributed.zookeeper

import org.apache.zookeeper.Watcher.Event.KeeperState
import org.apache.zookeeper.ZooKeeper
import java.util.concurrent.CountDownLatch

fun zooConnect(
    hostname: String,
    onStateChange: (KeeperState) -> Unit = { println("State change: $it") },
    onInit: (ZooKeeper) -> Unit
) {
    val countdown = CountDownLatch(1)
    var isInitialized = false

    ZooKeeper(hostname, 2000) { we ->
        if (!isInitialized) {
            when (we.state) {
                KeeperState.SyncConnected,
                KeeperState.ConnectedReadOnly -> {
                    countdown.countDown()
                    isInitialized = true
                }
                else -> {}
            }
        }

        onStateChange(we.state)
    }.run {
        countdown.await()
        use { onInit(it) }
    }
}