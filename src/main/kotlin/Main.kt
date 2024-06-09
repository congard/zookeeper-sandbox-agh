package pl.edu.agh.distributed.zookeeper

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

data class Node(val path: String)

fun main() {
    val hostname = System.getenv("ZK_HOSTNAME") ?: "127.0.0.1:2181"

    zooConnect(hostname) { zk ->
        val nodes = mutableStateListOf<Node>()

        zk(zk) { path, event ->
            println("$path: $event")

            when (event) {
                Event.Added -> nodes.add(Node(path))
                Event.Deleted -> nodes.removeIf { it.path == path }
            }
        }

        application {
            Window(title = "ZooKeeper", onCloseRequest = ::exitApplication) {
                App(nodes)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun NodeList(nodes: List<Node>) {
    LazyColumn {
        items(nodes) { node ->
            Surface(onClick = { println(node) }, shape = MaterialTheme.shapes.large) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Text(text = node.path, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}

@Composable
@Preview
private fun App(nodes: List<Node>) {
    MaterialTheme {
        Column {
            TopAppBar(title = {
                Text("Node count: ${nodes.size}")
            }, actions = {
                // RowScope here, so these icons will be placed horizontally
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(Icons.Filled.Info, contentDescription = "Info")
                }
            })

            NodeList(nodes)
        }
    }
}
