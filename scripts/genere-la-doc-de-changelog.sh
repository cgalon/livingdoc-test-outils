#!/usr/bin/env bash

# Génère un changelog avec un plugin Maven
mvn generate-sources -Pgenere-la-documentation-autre

# Génère un changelog avec un script JS
node scripts/release-notes.js > target/generated-docs/changelog-cdv.json


