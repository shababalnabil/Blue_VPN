#!/bin/bash

# Check if URL argument is provided
if [ -z "$1" ]; then
  echo "Usage: $0 <URL>"
  exit 1
fi

# The URL to download the file from
URL=$1

# Directory where the file will be saved
SCRIPT_DIR=$(pwd)
APP_DIR="$SCRIPT_DIR/app"

# Create the 'app' directory if it doesn't exist
if [ ! -d "$APP_DIR" ]; then
  mkdir "$APP_DIR"
  echo "Created directory: $APP_DIR"
fi

# File name
FILENAME="google-services.json"

# Full path where the file will be saved
FILE_PATH="$APP_DIR/$FILENAME"

# Download the file using curl
echo "Downloading $FILENAME from $URL..."
curl -o "$FILE_PATH" "$URL"

# Check if the download was successful
if [ $? -eq 0 ]; then
  echo "File downloaded successfully to $FILE_PATH"
else
  echo "Error downloading file. Please check the URL and try again."
  exit 1
fi