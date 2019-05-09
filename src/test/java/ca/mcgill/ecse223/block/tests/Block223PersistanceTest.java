package ca.mcgill.ecse223.block.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;

public class Block223PersistanceTest {

	@Test
	public void test() {
		Block223 block223 = Block223Application.getBlock223();

		List<Game> games = block223.getGames();
	}

}
