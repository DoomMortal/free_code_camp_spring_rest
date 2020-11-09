package com.example.demo.dao

import com.example.demo.model.Person
import java.util.*

interface PersonDao {

    fun insertPerson(id: UUID, person: Person): Int

    fun insertPerson(person: Person): Int {
        val id = UUID.randomUUID()
        return insertPerson(id, person)
    }

    fun selectAllPeople(): List<Person?>?

    fun selectPersonById(id: UUID): Optional<Person>

    fun deletePerson(id: UUID): Int

    fun updatePersonById(id: UUID, update: Person): Int

}