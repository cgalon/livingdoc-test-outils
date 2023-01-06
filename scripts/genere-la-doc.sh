#!/usr/bin/env bash
mvn verify -P documentation,springboot

cp $PWD/src/doc/index/index.html target/generated-docs/

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

java -cp target/livingdoc-jar-with-dependencies.jar fr.pe.incub.livingdoc.qdox.ExplorateurDeClasses

mvn site:site -P documentation,springboot

mvn io.qameta.allure:allure-maven:report -P documentation,springboot

cp -f $PWD/src/doc/graphiques_js/*.html target/generated-docs/

firefox target/generated-docs/index.html &
