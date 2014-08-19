package org.jaalon.bookmark

import spock.lang.Specification

class BookmarkTest extends Specification {
    def "Add a bookmark"() {
        given: "A memory storage"
            Storage inMemoryStorage = new InMemoryStorage()
        when: "I add two bookmarks"
            Bookmark bookmark = new BookmarkAdder(inMemoryStorage).add("title", "http://url.com")
            Bookmark bookmark2 = new BookmarkAdder(inMemoryStorage).add("title2", "http://url2.com")
        then: "The bookmark are retrieved and corresponding"
            Bookmark retrievedBookmark = new BookmarkRetriever(inMemoryStorage).retrieve(bookmark.id)
            Bookmark retrievedBookmark2 = new BookmarkRetriever(inMemoryStorage).retrieve(bookmark2.id)
            retrievedBookmark.correspondsTo(bookmark)
            !retrievedBookmark.correspondsTo(bookmark2)
            retrievedBookmark2.correspondsTo(bookmark2)
            !retrievedBookmark2.correspondsTo(bookmark)
    }
}
