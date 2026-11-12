#!/bin/bash
set -e
cd "$(dirname "$0")"
if ! javac -version >/dev/null 2>&1; then
  echo "Нужен JDK 17 или новее (java и javac)."
  exit 1
fi
mkdir -p build
javac --release 17 -encoding UTF-8 -d build src/*.java
java -Dfile.encoding=UTF-8 -cp build Main
