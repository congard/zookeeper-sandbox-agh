#!/bin/bash

# This script initializes basic environment.
# Do not use this script manually!

curr_dir="$PWD"
dir_name=$(basename -- "$curr_dir")

echo "Current directory: $curr_dir"

if [ "$dir_name" == "scripts" ]; then
  new_dir=$(realpath "$curr_dir/..")
  echo "Changing directory to $new_dir"
  cd "$new_dir" || exit
fi

# shellcheck disable=SC2034
zk_path="bin/apache-zookeeper"
