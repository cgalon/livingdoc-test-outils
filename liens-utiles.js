function listeDesDonnees() {
    let liens = [
        {
            titre: "Présentations du sujet",
            liste: [
                {
                    url: "https://youtu.be/Tw-wcps7WqU",
                    description: "Une des premières conférences de Cyrille Martraire sur le sujet."
                },
                {
                    url: "https://www.synolia.com/synolab/outils/living-documentation-pourquoi-ou-et-comment/",
                    description: "Résumé d'une conf sur la living doc."
                },
            ]
        },
        {
            titre: "Langages",
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
            titre: "Génération de documentation à partir du code",
            liste: [
                {
                    url: "https://www.sphinx-doc.org/en/master/",
                    description: "Génération de doc à partir de code Python."
                },
            ]
        },
        {
            titre: "Documentation d'API",
            liste: [
                {
                    url: "https://raml.org/",
                    description: "Modélisation d'API REST."
                },
                {
                    url: "https://www.openapis.org/",
                    description: "Modélisation d'API synchrone."
                },
                {
                    url: "https://www.asyncapi.com/",
                    description: "Modélisation d'API asynchrone."
                },
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
                    url: "https://docusaurus.io/",
                    description: "Outil de génération de sites documentaires."
                },
                {
                    url: "https://jekyllrb.com/",
                    description: "Générateur de site statique en Ruby."
                },
                {
                    url: "https://revealjs.com/",
                    description: "Framework de création de présentations en HTML."
                },
                {
                    url: "https://introjs.com/",
                    description: "Framework de guidance applicative."
                },
                {
                    url: "https://www.picklesdoc.com/",
                    description: "Mise en forme de fichiers features de Cucumber. Uniquement pour Windows."
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
