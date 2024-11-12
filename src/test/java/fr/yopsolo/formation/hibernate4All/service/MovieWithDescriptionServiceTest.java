package fr.yopsolo.formation.hibernate4All.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.yopsolo.formation.hibernate4All.config.PersistenceConfigTest;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PersistenceConfigTest.class })
@SqlConfig(dataSource = "dataSourceH2Test", transactionManager = "transactionManagerDeTest")
@Sql(value = { "/datas/init-data-moviewithdescription.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
class MovieWithDescriptionServiceTest {

	@Autowired
	private MovieWithDescriptionService service;

	@Test
	void testUpdateDescription() {
		service.updateDescription(-2L, "autre description");

	}

}
