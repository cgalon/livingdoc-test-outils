#!/usr/bin/env bash
docker run --rm -v $PWD/src/doc/asciidoctor:/documents/ asciidoctor/docker-asciidoctor asciidoctor *.adoc
docker run --rm -v $PWD/src/doc/asciidoctor:/documents/ asciidoctor/docker-asciidoctor asciidoctor-pdf *.adoc
docker run --rm -v $PWD/src/doc/dailies:/documents/ asciidoctor/docker-asciidoctor asciidoctor-pdf *.adoc
mv -f $PWD/src/doc/asciidoctor/*.html target/generated-docs/
mv -f $PWD/src/doc/asciidoctor/*.pdf target/generated-docs/
mv -f $PWD/src/doc/dailies/*.pdf target/generated-docs/
cp $PWD/src/doc/asciidoctor/*.png target/generated-docs/

cat src/doc/index/notes-utilisation-outils-entete.md src/doc/notes-utilisation-outils/*.md > target/generated-docs/notes-utilisation-outils.md
docker run --rm -v $PWD/target/generated-docs:/documents/ asciidoctor/docker-asciidoctor asciidoctor-pdf notes-utilisation-outils.md
