#!/usr/bin/env node
'use strict';

const { lstatSync, readdirSync } = require('fs')
const { join } = require('path')

const isDirectory = source => lstatSync(source).isDirectory();

const getDirectories = source =>
    readdirSync(source).map(name => join(source, name)).filter(isDirectory);


var cheminDuProjetRacineGit = "/home/icga1070/git/incubateur-cdv";
var listeDesProjetsGit = getDirectories(cheminDuProjetRacineGit);

listeDesProjetsGit.forEach(function(unCheminDeProjet){
    console.log("********************************************************");
    console.log("Projet en cours de traitement : " + unCheminDeProjet);
    console.log("********************************************************");
    traiteLaListeDesCommitsDUnRepertoire(unCheminDeProjet);

});

/*
 * Fonctions
 */

function traiteLaListeDesCommitsDUnRepertoire(cheminDuRepertoire) {
    var gitlog = require('gitlog')
        , options =
        {
            repo: cheminDuRepertoire
            , number: 2000
            , fields:
            ['subject',
                'authorName',
                'authorDate'
            ]
            , execOptions:
            {
                maxBuffer: 10240 * 1024
            }
        };

    gitlog(options, function (error, commits) {
        commits.forEach(function (commit, index) {
            console.log(index + ") (" + commit.authorDate + ") " + commit.subject + " - " + commit.authorName);
        });
    });

}

