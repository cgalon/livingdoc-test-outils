#!/usr/bin/env bash
mvn verify -P genere-la-doc
raml2html livingdoc.raml > target/generated-docs/livingdoc-api.html
node scripts/release-notes.js > target/generated-docs/changelog-cdv.json
