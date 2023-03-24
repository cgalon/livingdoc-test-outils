## Asciidoctor

### Description

Ecriture de docs en Ascidoctor.

### Site / docs complémentaires

[Source](https://asciidoctor.org/)

[CheatSheet](https://powerman.name/doc/asciidoc)

### Intégration

Utilisation d'une image Docker spécifique qui embarque tous les composants nécessaires.

```bash
docker pull asciidoctor/docker-asciidoctor
```

Utilisation d'un alias Linux :

```bash
alias genere-les-docs-de-ce-repertoire-en-pdf='docker run --rm -v $(pwd):/documents/ asciidoctor/docker-asciidoctor asciidoctor-pdf *.adoc'
```

### Commentaires

L'exemple de génération de document (HTML et PDF) utilisé dans ce projet est basé sur 2 articles sur Asciidoctor écrits en 2016 par l'Incubateur. 
Ils contiennent tout ce qu'il y a à savoir sur Asciidoctor.  

