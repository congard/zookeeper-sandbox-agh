#!/bin/bash

# This script generates configs and
# myid's for the specified servers

source "$(dirname "${BASH_SOURCE[0]}")/server-base.sh"

zk_base_data_dir="/tmp/zookeeper/zk"

echo "zk_base_data_dir=$zk_base_data_dir"

basic_cfg="# The number of milliseconds of each tick
tickTime=2000
# The number of ticks that the initial
# synchronization phase can take
initLimit=10
# The number of ticks that can pass between
# sending a request and getting an acknowledgement
syncLimit=5
"

server_port1=2888
server_port2=3888
client_port=2181

echo "Generating basic config"

# shellcheck disable=SC2154
for (( i=0; i < s_count; ++i )) ; do
  basic_cfg+=$'\n'"server.$i=localhost:$server_port1:$server_port2"
  ((server_port1++))
  ((server_port2++))
done

echo "Generating individual config for each server"

for (( i=0; i < s_count; ++i )) ; do
  # shellcheck disable=SC2154
  curr_cfg_path="$zk_base_cfg_path$i"

  mkdir -p "$curr_cfg_path"

  # append clientPort and dataDir vars and write to file
  echo "$basic_cfg

clientPort=$client_port
dataDir=$zk_base_data_dir$i" > "$curr_cfg_path/zoo.cfg"

  ((client_port++))
done

echo "Generating myid files"

for (( i=0; i < s_count; ++i )) ; do
  curr_data_dir="$zk_base_data_dir$i"
  mkdir -p "$curr_data_dir"
  echo "$i" > "$curr_data_dir/myid"
done

echo "Done"