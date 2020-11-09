package com.example.demo.dao

import com.example.demo.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.util.*

@Repository("postgres")
class PersonDataAccessService @Autowired constructor(private var jdbcTemplate: JdbcTemplate?) : PersonDao {

    /*fun PersonDataAccessService {
        this.jdbcTemplate = jdbcTemplate
    }*/

    override fun insertPerson(id: UUID, person: Person): Int {
        val sql = "INSERT INTO person (id, name) VALUES (?, ?)"
        return jdbcTemplate!!.update(sql, id, person.getName())
    }

    override fun selectAllPeople(): List<Person>? {
        val sql = "SELECT id, name FROM person"
        return jdbcTemplate!!.query(sql) { resultSet: ResultSet, i: Int ->
            val id = UUID.fromString(resultSet.getString("id"))
            val name = resultSet.getString("name")
            Person(id, name)
        }
    }

    override fun selectPersonById(id: UUID): Optional<Person> {
        val sql = "SELECT id, name FROM person WHERE id = ?"
        val person = jdbcTemplate!!.queryForObject(sql, arrayOf<Any>(id)) { resultSet: ResultSet, i: Int ->
            val personId = UUID.fromString(resultSet.getString("id"))
            val name = resultSet.getString("name")
            Person(personId, name)
        }
        return Optional.ofNullable(person)
    }

    override fun deletePerson(id: UUID): Int {
        val sql = "DELETE FROM person WHERE id = ?"
        return jdbcTemplate!!.update(sql, id)
    }

    override fun updatePersonById(id: UUID, update: Person): Int {
        val sql = "UPDATE person SET name = ? WHERE id = ?"
        return jdbcTemplate!!.update(sql, update.getName(), id)
    }

}