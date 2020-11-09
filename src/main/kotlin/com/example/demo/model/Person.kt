package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.validation.constraints.NotBlank

class Person(@JsonProperty("id") uuid: UUID?, @JsonProperty("name") name: String?) {

    private var uuid: UUID? = null
    private var name: @NotBlank String? = null

    init {
        this.uuid = uuid
        this.name = name
    }

    fun getUuid(): UUID? {
        return uuid
    }

    fun getName(): String? {
        return name
    }

}