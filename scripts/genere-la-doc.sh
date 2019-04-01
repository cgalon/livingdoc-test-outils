#!/usr/bin/env bash
mvn verify -P genere-la-doc

raml2html livingdoc.raml > target/generated-docs/livingdoc-api.html

node scripts/release-notes.js > target/generated-docs/changelog-cdv.json

docker run --rm -v $PWD/src/doc/asciidoctor:/documents/ asciidoctor/docker-asciidoctor asciidoctor *.adoc
docker run --rm -v $PWD/src/doc/asciidoctor:/documents/ asciidoctor/docker-asciidoctor asciidoctor-pdf *.adoc
mv -f $PWD/src/doc/asciidoctor/*.html target/generated-docs/
mv -f $PWD/src/doc/asciidoctor/*.pdf target/generated-docs/
cp $PWD/src/doc/asciidoctor/*.png target/generated-docs/
