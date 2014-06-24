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
        def title = "a URL"
        def url = "http://myurl.com"
        def repositoryName = "Repository"
        def theBookmark = new Bookmark(title, url)

        setup: "Create a new repository"
        def repository = new Repository(repositoryName)

        when: "I add a bookmark to the repository"
        int bookmarkIndex = repository.add(theBookmark)

        then: "the bookmark should be in the repository"
        !repository.isEmpty()
        repository.getSize() == 1
        repository.contains(theBookmark.index)

        when: "I retrieve the bookmark from its index"
        Bookmark retrievedBookmark = repository.getBookmark(bookmarkIndex)

        then: "The retrieved bookmark should have its information"
        retrievedBookmark.title == title
        retrievedBookmark.url == url

        when: "I remove the bookmark"
        repository.remove(bookmarkIndex)

        then: 'The bookmark have been removed from repository'
        repository.isEmpty()
        repository.getSize() == 0
        !repository.contains(retrievedBookmark.index)
        !repository.getBookmark(bookmarkIndex)
    }

}
