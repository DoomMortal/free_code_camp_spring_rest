package com.example.demo.service

import com.example.demo.dao.PersonDao
import com.example.demo.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonService @Autowired constructor(@Qualifier("postgres") private var personDao: PersonDao?) {

    /*@Autowired
    fun PersonService(@Qualifier("postgres") personDao: PersonDao?) {
        this.personDao = personDao
    }*/

    fun addPerson(person: Person): Int {
        return personDao!!.insertPerson(person)
    }

    fun getAllPeople(): List<Person?>? {
        return personDao!!.selectAllPeople()
    }

    fun getPersonById(id: UUID): Optional<Person>? {
        return personDao!!.selectPersonById(id)
    }

    fun deletePerson(id: UUID): Int {
        return personDao!!.deletePerson(id)
    }

    fun updatePerson(id: UUID, newPerson: Person): Int {
        return personDao!!.updatePersonById(id, newPerson)
    }

}