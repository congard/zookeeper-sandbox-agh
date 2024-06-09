#!/bin/bash

# This script initializes basic server environment
# and is used in the cfg and run scripts.
# Do not use this script manually!

source "$(dirname "${BASH_SOURCE[0]}")/init.sh"

# shellcheck disable=SC2034
zk_base_cfg_path="/tmp/zookeeper/cfg"

# shellcheck disable=SC2154
echo "zk_path=$zk_path"
echo "zk_base_cfg_path=$zk_base_cfg_path"

if [ $# -gt 1 ]; then
  echo "Illegal number of arguments: 0 or 1 expected"
fi

if [ $# -eq 0 ]; then
  s_count=3
else
  # shellcheck disable=SC2034
  s_count=$1
fi

echo "s_count=$s_count"