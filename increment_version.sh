#!/bin/bash

# Read the current version from the pom.xml
current_version=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

# Extract the numeric part of the version (e.g., "1.6-SNAPSHOT" becomes "1.6")
numeric_version=$(echo "$current_version" | sed -E 's/([0-9]+\.[0-9]+).*$/\1/')

# Increment the numeric version by 1
new_numeric_version=$(echo "$numeric_version + 1" | bc)

# Create the new version by appending -SNAPSHOT
new_version="$new_numeric_version-SNAPSHOT"

# Set the new version in the pom.xml
mvn versions:set -DnewVersion="$new_version"
