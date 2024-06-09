#!/bin/bash

source "$(dirname "${BASH_SOURCE[0]}")/init.sh"

mkdir -p "bin"
cd bin || exit

url="https://dlcdn.apache.org/zookeeper/zookeeper-3.8.4/apache-zookeeper-3.8.4-bin.tar.gz"
file="$(basename "$url")"

pwd

wget "$url" -O "./$file"

tar -xzf "$file"
rm "$file"

rm -rf apache-zookeeper
mv apache-zookeeper* apache-zookeeper