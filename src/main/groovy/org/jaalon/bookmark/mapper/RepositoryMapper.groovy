package org.jaalon.bookmark.mapper
import org.jaalon.bookmark.model.Repository

interface RepositoryMapper {

    Repository insert(Repository repository)

    Repository load(Number index)
}