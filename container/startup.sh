#!/usr/bin/env bash

PROJECT_HOME="${HOME}/project-home"
APP_PROPERTIES="${PROJECT_HOME}/application.properties"
APP_YML="${PROJECT_HOME}/application.yml"
APP_YAML="${PROJECT_HOME}/application.yaml"

function link_application_config() {
    if [[ -f "${1}" ]]; then
        local TARGET=/app/$(basename ${1})
        echo "Create symlink for ${1} to ${TARGET}"
        ln -s "${1}" "${TARGET}"
    fi
}

link_application_config ${APP_PROPERTIES}
link_application_config ${APP_YML}
link_application_config ${APP_YAML}

java -jar app.jar
