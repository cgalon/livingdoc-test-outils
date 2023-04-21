#!/usr/bin/env bash

./scripts/genere-la-doc-des-tests.sh

# Récupère la page d'index des documents générés
cp $PWD/src/doc/index/index.html target/generated-docs/

echo "******************** Génération des docs en asciidoc ********************"
./scripts/genere-la-doc-avec-asciidoc.sh

echo "******************** Génération des docs de l'API ********************"
./scripts/genere-la-doc-de-l-api.sh

echo "******************** Génération des changelogs ********************"
./scripts/genere-la-doc-de-changelog.sh

echo "******************** Récupération des exemples Javascript ********************"
# Récupère les exemples d'utilisation de libs graphiques JS
cp -f $PWD/src/doc/graphiques_js/*.html target/generated-docs/

# Génération de docs à partir du parsing du code source
java -cp target/livingdoc-jar-with-dependencies.jar fr.pe.incub.livingdoc.qdox.ExplorateurDeClasses

echo "******************** Affichage des résultats ********************"
nohup firefox target/generated-docs/index.html &

# Nettoie les fichiers d'erreur dûs aux tests avec Spring et AspectJ
rm -f ajcore*.txt

echo "******************** Execution terminée ********************"