package org.jaalon.bookmark

import org.jaalon.bookmark.model.Bookmark
import org.jaalon.bookmark.model.Repository
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

    def "Add bookmark to repository and retrieve it" () {
        def title = "a URL"
        def url = "http://myurl.com"
        def repositoryName = "Repository"
        Bookmark theBookmark = new Bookmark(title, url)

        given: "A new repository and a bookmark"
        def repository = new Repository(repositoryName)

        when: "I add the bookmark to the repository"
        int bookmarkIndex = repository.add(theBookmark)

        then: "the bookmark is added to the repository"
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

    def "Operations on bookmarks"() {
        given: "A repository and several bookmarks"
        Bookmark b1 = new Bookmark("title1", "url1")
        Bookmark b2 = new Bookmark("title2", "url2")
        Bookmark b3 = new Bookmark("title3", "url3")
        Repository repository = new Repository("the repository")

        when: "I add all bookmarks"
        def bookmarkIndexes = repository.add([b1, b2, b3])

        then: "I should retrieve all indexes"
        bookmarkIndexes == [b1.index, b2.index, b3.index]

        when: "I remove a bookmark"
        repository.remove(b2)

        then: "The bookmark should not be accessible in repository anymore"
        !repository.getBookmark(b2.index)
    }

    def "Tag a bookmark" () {
        def aTag = "a tag"
        def anOtherTag = "an other tag"

        given: "A repository with several bookmarks"
        Bookmark b1 = new Bookmark("title1", "url1")
        Bookmark b2 = new Bookmark("title2", "url2")
        Bookmark b3 = new Bookmark("title3", "url3")
        Repository repository = new Repository("the repository")

        repository.add([b1, b2, b3])

        when: "I tag several bookmarks"
        b2.tag(aTag)
        b3.tag([aTag, anOtherTag])

        then: "When I search for bookmarks by tag, I should retrieve the tagged bookmarks"
        def bookmarks = repository.find(aTag)
        bookmarks.size() == 2
        bookmarks.containsAll(b2, b3)

        def otherBookmarks = repository.find(anOtherTag)
        otherBookmarks.size() == 1
        bookmarks.contains(b3)
    }

}
