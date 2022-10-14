#!/usr/bin/env node
'use strict';

const {lstatSync, readdirSync} = require('fs')
const {join} = require('path')

const isDirectory = source => lstatSync(source).isDirectory();

const getDirectories = source =>
    readdirSync(source).map(name => join(source, name)).filter(isDirectory);


var cheminDuProjetRacineGit = "/home/icga1070/git/github.com/cgalon";
var listeDesProjetsGit = getDirectories(cheminDuProjetRacineGit);

var listeDesFeaturesDeTousLesProjets = {};

// Crée la liste des commits pour tous les projets
listeDesProjetsGit.forEach(function (unCheminDeProjet) {
    var nomDuProjet = recupereLeNomDuProjet(unCheminDeProjet);
    var listeDesCommits = traiteLaListeDesCommitsDUnRepertoire(unCheminDeProjet);
    listeDesFeaturesDeTousLesProjets[nomDuProjet] = listeDesCommits;
});

// Sauvegarde la liste des commits
var donneesEnJSON = JSON.stringify(listeDesFeaturesDeTousLesProjets);
console.log(donneesEnJSON);



/*
 * ********************
 * Fonctions
 * ********************
 */
function metEnFormeLeMessage(commit) {

    return "[" + commit.authorDate.split(" ")[0] + "] " + commit.subject + " - " + commit.authorName;
}

function recupereLeNomDuProjet(cheminDuRepertoire) {
    var elementsDuChemin = cheminDuRepertoire.split("/");
    var nomDuProjet = elementsDuChemin[elementsDuChemin.length - 1];
    return nomDuProjet;
}

function traiteLaListeDesCommitsDUnRepertoire(cheminDuRepertoire) {
    var gitlog = require('gitlog')
        , options =
        {
            repo: cheminDuRepertoire
            , number: 50
            , fields:
                [
                    'hash'
                    , 'abbrevHash'
                    , 'subject'
                    , 'authorName'
                    , 'authorDate'
                ]
            , execOptions:
                {
                    maxBuffer: 10240 * 1024
                }
        };

    let listeDeCommitsInitiale = gitlog(options);

    var listeFiltree = listeDeCommitsInitiale.map(function(commit){
        return commit;
    }).filter(function (commit) {
        // On filtre les commits automatiques
        return (!commit.subject.includes("[auto]") && !commit.subject.includes("Jenkins"));
    }).filter(function (commit) {
        // On filtre tout ce qui n'est pas une "feature"
        return (commit.subject.includes("feat("));
    }).map(function (commit) {
        // On fabrique le message en sortie
        return metEnFormeLeMessage(commit);
    });

    return listeFiltree;
}

