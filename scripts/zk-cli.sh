#!/bin/bash

# This script can be used to connect to the server

source "$(dirname "${BASH_SOURCE[0]}")/init.sh"

if [ $# -gt 1 ]; then
  echo "Illegal number of arguments: 0 or 1 expected"
fi

if [ $# -eq 0 ]; then
  hostname="127.0.0.1:2181"
else
  hostname=$1
fi

echo "hostname=$hostname"

# shellcheck disable=SC2154
"$zk_path/bin/zkCli.sh" -server "$hostname"