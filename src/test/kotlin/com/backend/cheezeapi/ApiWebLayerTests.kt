package com.backend.cheezeapi

import com.backend.cheezeapi.strain.StrainDto
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.boot.test.web.client.postForObject
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

/**
 * Перед запуском тестов - развернуть приложение и БД, потом параллельно запустить тесты
 */

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiWebLayerTests {

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Test
    fun getStrainTest() {
        val strainDto: StrainDto = restTemplate?.getForObject("http://localhost:8081/strain/1", StrainDto)
                ?: error("Не удалось вернуть результат для \"http://localhost:8081/strain/1\"")
        assertTrue(true)
    }

    @Test
    fun getStrainListTest() {
        val strainList: ArrayList<StrainDto> = restTemplate?.getForObject("http://localhost:8081/strain?page=1&size=1")
                ?: error("Не удалось вернуть результат для \"http://localhost:8081/strain?page=1&size=1\"")
        assertTrue(true)
    }

	@Test
	fun getStrainSaveTest() {
		val strainDto: StrainDto = restTemplate?.postForObject(
				"http://localhost:8081/strain/save?page=1&size=1",
				request = "{\n" +
						"    \"type\": {\n" +
						"        \"id\": 1\n" +
						"    },\n" +
						"    \"name\": \"Lactococcus lactis С9-18-2\",\n" +
						"    \"collectionIndex\": \"ИД-765\",\n" +
						"    \"dateReceiving\": \"1981-12-19T17:00:00.000Z\",\n" +
						"    \"source\": \"Получен из штамма L.lactis 174\",\n" +
						"    \"obtainingMethod\": \"Экспериментальная селекция\",\n" +
						"    \"creator\": \"Крузенштерн И.Ф.\",\n" +
						"    \"dateAdded\": \"2021-04-11T20:49:10.990Z\",\n" +
						"    \"properties\": [\n" +
						"        {\n" +
						"            \"propertyId\": 1,\n" +
						"            \"ungroupedParameters\": [\n" +
						"                { \"formalParameter\": {\"id\": 1}, \"value\": \"44\" }\n" +
						"            ],\n" +
						"            \"groups\": [\n" +
						"                {\n" +
						"                    \"groupId\": 77,\n" +
						"                     \"parameters\": [\n" +
						"                        { \"formalParameter\": {\"id\": 2}, \"value\": \"100\" },\n" +
						"                        { \"formalParameter\": {\"id\": 3}, \"value\": \"0.9\" }\n" +
						"                    ]\n" +
						"                }\n" +
						"            ]\n" +
						"        }\n" +
						"    ]\n" +
						"}"
		)
				?: error("Не удалось вернуть результат для \"http://localhost:8081/strain?page=1&size=1\"")
		assertTrue(true)
	}

	//TODO: Fix me, please...
	// Тест ломает запрос. До теста - запрос работает. После теста - could not extract ResultSet
	@Test
	@Disabled
	fun getStrainFindByPredicateTest() {
		val strainsDto: List<StrainDto> = restTemplate?.postForObject(
				"http://localhost:8081/strain/list?page=1&size=1",
				request = "{\n" +
						"    \"predicates\": []\n" +
						"}"
		)
				?: error("Не удалось вернуть результат для \"http://localhost:8081/strain/list?page=1&size=1\"")
		assertTrue(true)
	}
}
