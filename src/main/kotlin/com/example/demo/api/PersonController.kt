package com.example.demo.api

import com.example.demo.model.Person
import com.example.demo.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

@RequestMapping("api/v1/person")
@RestController
class PersonController @Autowired constructor(private var personService: PersonService?) {

    /*@Autowired
    fun PersonController(personService: PersonService?) {
        this.personService = personService
    }*/

    @PostMapping
    fun addPerson(@RequestBody person: @Valid @NotNull Person) {
        personService!!.addPerson(person)
    }

    @GetMapping
    fun getAllPeople(): List<Person?>? {
        return personService!!.getAllPeople()
    }

    @GetMapping(path = ["/{id}"])
    fun getPersonById(@PathVariable("id") id: UUID): Person? {
        return personService!!.getPersonById(id)!!.orElse(null)
    }

    @DeleteMapping(path = ["{id}"])
    fun deletePersonById(@PathVariable("id") id: UUID) {
        personService!!.deletePerson(id)
    }

    @PutMapping(path = ["{id}"])
    fun updatePerson(@PathVariable("id") id: UUID, @RequestBody personToUpdate: @Valid Person) {
        personService!!.updatePerson(id, personToUpdate)
    }

}