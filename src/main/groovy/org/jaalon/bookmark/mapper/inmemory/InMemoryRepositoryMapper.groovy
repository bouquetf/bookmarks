package org.jaalon.bookmark.mapper.inmemory

import org.jaalon.bookmark.mapper.RepositoryMapper
import org.jaalon.bookmark.model.Repository

class InMemoryRepositoryMapper implements RepositoryMapper {
    Map<Number, Repository> repositories = new HashMap<Number, Repository> ()
    Number index = 0

    @Override
    Repository insert(Repository repository) {
        index ++
        Repository repositoryToInsert = new Repository(repository)
        repositoryToInsert.index = index
        repositories.put(index, repositoryToInsert)

        return repositoryToInsert
    }

    @Override
    Repository load(Number i) {
        Repository repository = repositories.get(i)
        return repository ?: new Repository(repository)
    }
}
