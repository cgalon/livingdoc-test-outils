#!/usr/bin/env bash

# Lance le build et les outils de tests et de doc associés
mvn verify -P genere-la-documentation-des-tests
# Génère le site Maven associé à ce projet
mvn site:site -P genere-le-site-maven
