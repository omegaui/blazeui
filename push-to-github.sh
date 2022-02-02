#!/bin/sh

clear

echo "Copy This Token"

/home/ubuntu/Documents/./my-token

echo "Adding Files ..."
git add .

echo "Enter Commit Message"
read msg

echo "Writing Commit ..."
git commit -m "$msg"

echo "Pushing to GitHub!"
git push -u origin main

