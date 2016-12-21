#!/usr/bin/env sh
cd "$(dirname "$0")"

set -e

npm install
ng test --single-run
ng build -prod --aot
