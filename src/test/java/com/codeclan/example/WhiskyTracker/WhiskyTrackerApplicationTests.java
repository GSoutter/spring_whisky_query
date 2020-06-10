package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	DistilleryRepository distilleryRepository;

	@Autowired
	WhiskyRepository whiskyRepository;

	@Test
	public void canGetWhiskiesByYear() {
		List<Whisky> found = whiskyRepository.findByYear(2018);
		assertEquals(6, found.size());
	}

	@Test
	public void canGetDistilleriesByRegion() {
		List<Distillery> found = distilleryRepository.findByRegion("Islay");
		assertEquals(2, found.size());
	}

	@Test
	public void canGetWhiskyByDistilleryAndAge() {
		List<Whisky> found = whiskyRepository.findByDistilleryNameAndAge("Talisker", 1);
		assertEquals(2, found.size());
		assertEquals("Skye", found.get(1).getName());
	}

	@Test
	public void canGetWhiskyFromRegion() {
		List<Whisky> found = whiskyRepository.findByDistilleryRegion("Island");
		assertEquals(6, found.size());
		assertEquals("Skye", found.get(3).getName());
	}

	@Test
	public void canGetDistileriesWith12YoWhiskies() {
		List<Distillery> found = distilleryRepository.findByWhiskiesAge(18);
		assertEquals(2, found.size());
		assertEquals(6L, found.get(0).getId().longValue());

	}


}
