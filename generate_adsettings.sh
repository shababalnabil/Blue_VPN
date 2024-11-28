#!/bin/bash

# Get the directory where the script is located
SCRIPT_DIR="$(dirname "$0")"

# Define the target directory and file path relative to the script directory
TARGET_DIR="$SCRIPT_DIR/app/src/main/java/com/webihostapp/xprofreevpnapp"
FILENAME="AdSettings.java"

# Check if at least one URL is provided
if [ "$#" -lt 1 ]; then
    echo "Usage: $0 <AD_URL_1> [<AD_URL_2> ...]"
    exit 1
fi

# Create the target directory if it doesn't exist
mkdir -p "$TARGET_DIR"

# Create the AdSettings.java file
cat > "$TARGET_DIR/$FILENAME" <<EOL
package com.webihostapp.xprofreevpnapp;

public class AdSettings {

EOL

# Loop through the arguments to add the ad URLs as constants
index=1
for url in "$@"; do
    echo "    public static final String AD_URL_$index = \"$url\";" >> "$TARGET_DIR/$FILENAME"
    index=$((index + 1))
done

# Close the class
cat >> "$TARGET_DIR/$FILENAME" <<EOL
}
EOL

# Success message
echo "AdSettings.java has been generated at $TARGET_DIR/$FILENAME"
