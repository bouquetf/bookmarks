package org.jaalon.bookmark

import org.jaalon.bookmark.mapper.RepositoryMapper
import org.jaalon.bookmark.mapper.inmemory.InMemoryRepositoryMapper
import org.jaalon.bookmark.model.Bookmark
import org.jaalon.bookmark.model.Repository
import spock.lang.Specification

class StorageSpec extends Specification {
    def "create, store and retrieve a repository with its bookmarks"() {
        given: "A repository mapper"
        RepositoryMapper inMemoryRepositoryMapper = new InMemoryRepositoryMapper();

        when: "I store the repository via the mapper"
        Repository repository = new Repository("A repository")
        Bookmark bookmark1 = new Bookmark("title", "http://url")
        bookmark1.tag("tag1")
        bookmark1.tag("tag2")
        Bookmark bookmark2 = new Bookmark("title1", "http://url1")
        repository.add(bookmark1)
        repository.add(bookmark2)
        Repository repository1 = new Repository("An other repository")

        Repository insertedRepository = inMemoryRepositoryMapper.insert(repository)
        Repository insertedRepository1 = inMemoryRepositoryMapper.insert(repository1)

        then: "I can retrieve the repository from the mapper"
        Number index = insertedRepository.index
        Number index1 = insertedRepository1.index
        def retrievedRepository = inMemoryRepositoryMapper.load(index)
        def retrievedRepository1 = inMemoryRepositoryMapper.load(index1)

        repository == insertedRepository
        repository1 == insertedRepository1
        repository != insertedRepository1
        repository == retrievedRepository

        Bookmark retrievedBookmark = retrievedRepository.getBookmark("title")
        retrievedBookmark == bookmark1
        retrievedBookmark.getTags() == ["tag1", "tag2"]
        retrievedRepository.getBookmark("title1") == bookmark2
        repository1 == retrievedRepository1
        repository != retrievedRepository1
    }
}
