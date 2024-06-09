# Scripts

The scripts in this directory can be used to download Apache's Zookeeper,
configure servers, start them and launch CLI.

## Configuration

Make the scripts executable:

```bash
chmod +x zk-*.sh
```

## List

| Script            | Description                                                                                   |
|-------------------|-----------------------------------------------------------------------------------------------|
| `zk-installer.sh` | Downloads and unpacks Apache Zookeeper                                                        |
| `zk-cfg.sh`       | Creates configs for the specified server count (default value is 3)                           |
| `zk-run.sh`       | Launches the specified server count (default is 3). All servers could be terminated by CTRL+C |
| `zk-cli.sh`       | Starts client that connects to the specified server (default is `127.0.0.1:2181`)             |
| `server-base.sh`  | Helper script that is used by `zk-cfg.sh` and `zk-run.sh`                                     |
| `init.sh`         | Helper script that is used by the all above scripts                                           |

For more info see sources