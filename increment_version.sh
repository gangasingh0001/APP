#!/bin/bash

# Read the current version from the pom.xml
current_version=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

# Increment the version by 1
new_version=$(echo $current_version + 1 | bc)

# Set the new version in the pom.xml
mvn versions:set -DnewVersion=$new_version-SNAPSHOT
