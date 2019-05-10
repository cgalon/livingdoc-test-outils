#!/usr/bin/env bash
mvn verify -P genere-la-doc

mvn surefire-report:report
cp target/site/surefire-report.html target/generated-docs

raml2html api-securisee.raml > target/generated-docs/raml-api-securisee.html
raml2html api-comics.raml > target/generated-docs/raml-api-comics.html

node scripts/release-notes.js > target/generated-docs/changelog-cdv.json

docker run --rm -v $PWD/src/doc/asciidoctor:/documents/ asciidoctor/docker-asciidoctor asciidoctor *.adoc
docker run --rm -v $PWD/src/doc/asciidoctor:/documents/ asciidoctor/docker-asciidoctor asciidoctor-pdf *.adoc
docker run --rm -v $PWD/src/doc/dailies:/documents/ asciidoctor/docker-asciidoctor asciidoctor-pdf *.adoc
mv -f $PWD/src/doc/asciidoctor/*.html target/generated-docs/
mv -f $PWD/src/doc/asciidoctor/*.pdf target/generated-docs/
mv -f $PWD/src/doc/dailies/*.pdf target/generated-docs/
cp $PWD/src/doc/asciidoctor/*.png target/generated-docs/

cp $PWD/src/doc/index/index-test-spring-restdocs.adoc target/generated-docs/test-spring-restdocs
docker run --rm -v $PWD/target/generated-docs/test-spring-restdocs:/documents/ asciidoctor/docker-asciidoctor asciidoctor *.adoc
