package com.example.cleanarchitecture.data.base

import com.example.cleanarchitecture.domain.model.Model

interface EntityMapper<M : Model, ME : ModelEntity> {
    fun mapToDomain(entity: ME): M

    fun mapToEntity(model: M): ME
}