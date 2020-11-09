package com.example.demo.dao

import com.example.demo.model.Person
import java.util.*

class FakePersonDataAccessService : PersonDao {

    private val DB: MutableList<Person>? = ArrayList()

    override fun insertPerson(id: UUID, person: Person): Int {
        DB!!.add(Person(id, person.getName()))
        return 1
    }

    override fun selectAllPeople(): MutableList<Person>? {
        return DB
    }

    override fun selectPersonById(id: UUID): Optional<Person> {
        return DB!!.stream().filter { person: Person -> person.getUuid() == id }.findFirst()
    }

    override fun deletePerson(id: UUID): Int {
        val personMaybe = selectPersonById(id)
        if (personMaybe.isPresent) {
            return 0
        }
        DB!!.remove(personMaybe.get())
        return 1
    }

    override fun updatePersonById(id: UUID, update: Person): Int {

        return selectPersonById(id)?.map { person ->
            val indexOfPersonToUpdate = DB!!.indexOf(person)
            if (indexOfPersonToUpdate >= 0) {
                DB[indexOfPersonToUpdate] = Person(id, update.getName())
                return@map 1
            }
            0
        }!!.orElse(0)
    }

        /*return selectPersonById(id).map { person: Person ->
            val indexOfPersonToUpdate = DB!!.indexOf(person)
            if (indexOfPersonToUpdate >= 0) {
                DB[indexOfPersonToUpdate] = Person(id, update.getName())
                return@map 1
            }
            0
        }.orElse(0)*/

}