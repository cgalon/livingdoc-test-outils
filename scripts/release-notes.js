#!/usr/bin/env node
'use strict';

const {lstatSync, readdirSync} = require('fs')
const {join} = require('path')

const isDirectory = source => lstatSync(source).isDirectory();

const getDirectories = source =>
    readdirSync(source).map(name => join(source, name)).filter(isDirectory);


var cheminDuProjetRacineGit = "/home/icga1070/git/incubateur-cdv";
var listeDesProjetsGit = getDirectories(cheminDuProjetRacineGit);

listeDesProjetsGit.forEach(function (unCheminDeProjet) {
    traiteLaListeDesCommitsDUnRepertoire(unCheminDeProjet);

});

/*
 * Fonctions
 */

function metEnFormeLeMessage(commit) {

    return "[" + commit.projet + "] [" + commit.authorDate.split(" ")[0] + "] " + commit.subject + " - " + commit.authorName;
}

function traiteLaListeDesCommitsDUnRepertoire(cheminDuRepertoire) {
    var elementsDuChemin = cheminDuRepertoire.split("/");
    var nomDuProjet = elementsDuChemin[elementsDuChemin.length-1];

    console.log("********************************************************");
    console.log("Projet en cours de traitement : " + nomDuProjet);
    console.log("********************************************************");
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
        // On ajoute le nom du projet d'origine dans le commit
        commit.projet = nomDuProjet;
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

    console.log(listeFiltree);

}

