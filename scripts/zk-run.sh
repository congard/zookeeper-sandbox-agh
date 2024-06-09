#!/bin/bash

# This script runs all the servers

source "$(dirname "${BASH_SOURCE[0]}")/server-base.sh"

echo "Generating chained command"

cmd=""

# shellcheck disable=SC2154
for (( i=0; i < s_count; ++i )) ; do
  cmd+="$zk_path/bin/zkServer.sh --config "$zk_base_cfg_path$i" start-foreground & "
done

cmd+="wait"

echo "cmd=$cmd"

# https://stackoverflow.com/a/52033580/9200394
(trap 'kill 0' SIGINT; eval "$cmd")