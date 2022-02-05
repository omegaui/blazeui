#!/bin/sh
echo "Compiling Project Kode ..."

kotlinc -d bin -cp "res:". @.sources

if [ $? -eq 0 ]; then
    echo "Compilation Completed Successfully."
else
    echo "Compilation resulted in errors."
    exit 1
fi
