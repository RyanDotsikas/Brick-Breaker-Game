package ca.mcgill.ecse223.block.persistence;

import ca.mcgill.ecse223.block.model.Block223;

public class Block223Persistence {
	
	private static String filename = "data.thicc";
	
	public static void save(Block223 block223) {
		PersistenceObjectStream.serialize(block223);
	}
	
	public static Block223 load() {
		PersistenceObjectStream.setFilename(filename);
		Block223 btms = (Block223) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty BTMS
		if (btms == null) {
			btms = new Block223();
		}
		else {
			btms.reinitialize();
		}
		return btms;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}

}
