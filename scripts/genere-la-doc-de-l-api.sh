#!/usr/bin/env bash

# Génère la doc de l'API avec RAML
raml2html api-securisee.raml > target/generated-docs/raml-api-securisee.html
raml2html api-comics.raml > target/generated-docs/raml-api-comics.html

# Génère la doc de l'API avec Spring Rest-docs
# Nécessite d'avoir exécuté les tests avant
cp $PWD/src/doc/index/index-test-spring-restdocs.adoc target/generated-docs/test-spring-restdocs
docker run --rm -v $PWD/target/generated-docs/test-spring-restdocs:/documents/ asciidoctor/docker-asciidoctor asciidoctor *.adoc
