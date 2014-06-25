package org.jaalon.bookmark

import spock.lang.Specification

class RepositorySpec extends Specification {
    def "Create repository"() {
        def repositoryName = "Repository name"

        when: "I create a repository"
        Repository repository = new Repository(repositoryName);

        then: "The repository should be empty and have a name"
        repository.name == repositoryName
        repository.isEmpty()
    }

    def "Add bookmark to repository should and retrieve it" () {
        def theBookmark = "http://myurl.com"
        def repositoryName = "Repository"

        setup: "Create a new repository"
        def repository = new Repository(repositoryName)

        when: "I add a bookmark to the repository"
        repository.add(theBookmark)

        then: "the bookmark should be in the repository"
        !repository.isEmpty()
        repository.getSize() == 1
        repository.bookmarks.contains(theBookmark)

        when: "I remove the bookmark"
        repository.remove(theBookmark)

        then: 'The bookmark have been removed from repository'
        repository.isEmpty()
        repository.getSize() == 0
        !repository.bookmarks.contains(theBookmark)
    }

}
