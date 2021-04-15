package com.backend.cheezeapi.utils

import com.backend.cheezeapi.strain.StrainDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

class PaginationHelper {
    companion object {
        fun <T> getContent(repository: JpaRepository<T, Long>, pageNo: Long, pageSize: Long): List<T> {
            val paging: Pageable = PageRequest.of(pageNo.toInt(), pageSize.toInt())
            val page: Page<T> = repository.findAll(paging)

            return if (page.hasContent()) {
                page.content
            } else {
                ArrayList()
            }
        }
    }
}