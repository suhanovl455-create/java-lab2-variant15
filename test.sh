#!/bin/bash
set -e
cd "$(dirname "$0")"
mkdir -p build
javac --release 17 -encoding UTF-8 -d build src/*.java Checks.java
java -Dfile.encoding=UTF-8 -cp build Checks
