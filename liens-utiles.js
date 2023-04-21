function listeDesDonnees() {
    let liens = [
        {
            titre: "Langage",
            liste: [
                {
                    url: "https://docs.asciidoctor.org/asciidoctor/latest/",
                    description: "Documentation de référence Asciidoctor."
                },
                {
                    url: "https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet",
                    description: "Cheatsheet Markdown."
                },
            ]
        },
        {
            titre: "Article généraliste",
            liste: [
                {
                    url: "https://www.synolia.com/synolab/outils/living-documentation-pourquoi-ou-et-comment/",
                    description: "Résumé d'une conf sur la living doc."
                },
            ]
        },
        {
            titre: "Génération de documentation",
            liste: [
                {
                    url: "https://www.sphinx-doc.org/en/master/",
                    description: "Génération de doc à partir de code Python."
                }
            ]
        },
        {
            titre: "Mise en forme de documentation",
            liste: [
                {
                    url: "https://antora.org/",
                    description: "Outil de génération de sites documentaires."
                },
                {
                    url: "https://jekyllrb.com/",
                    description: "Générateur de site statique en Ruby."
                },
            ]
        },
        {
            titre: "Graphiques et diagrammes",
            liste: [
                {
                    url: "https://mermaid.js.org/#/",
                    description: "Générateur de diagrammes. Simple et puissant."
                },
                {
                    url: "https://www.chartjs.org/",
                    description: "Générateur de graphiques en JS. Opensource."
                },
            ]
        },
    ]
    return liens;
}
