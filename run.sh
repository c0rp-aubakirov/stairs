#!/usr/bin/env bash


function run_in_docker() {
    if [[ "$BACKGROUND" == "true" ]] ; then
        docker run -d -p $PORT:8080 c0rp/stairs:latest
    else
        docker run -it -p $PORT:8080 c0rp/stairs:latest
    fi
}

function show_help {
    usage="$(basename "$0") [OPTIONS] -- run application in docker

where:
    -p  port that application will use, default is 8080
    -d run in background, by default run in terminal
"
    echo "$usage"
}

# A POSIX variable
OPTIND=1         # Reset in case getopts has been used previously in the shell.

# Initialize our own variables:
output_file=""
verbose=0

PORT=8080
BACKGROUND="false"

while getopts "h?vp:sbtm:j" opt; do
    case "$opt" in
    h|\?)
        show_help
        exit 0
        ;;
    p)  PORT="$OPTARG"
        ;;
    d)  BACKGROUND="true"
    esac
done

shift $((OPTIND-1))

</dev/tcp/localhost/$PORT  >/dev/null 2>&1 && echo Port $PORT is busy. Run with -h to see help info && exit 1 || echo Port $PORT is not busy. Starting...

./mvnw clean install && run_in_docker

