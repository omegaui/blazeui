#!/bin/sh
echo "Compiling Project Kode ..."

kotlinc -d bin -cp "res:". @.sources

if [ $? -eq 0 ]; then
    echo "Kotlin Compilation Completed Successfully."
else
    echo "Kotlin Compilation resulted in errors."
    exit 1
fi


