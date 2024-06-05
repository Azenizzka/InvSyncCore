package ru.azenizzka.InvSyncCore.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SyncService {
	private final String SEPARATOR = File.separator;
	private final String DATA_PATH = "data";
	private final File DATA_DIR;

	public SyncService() throws IOException {
		File file = new File(DATA_PATH);
		if (!file.exists() && !file.mkdirs()){
			throw new IOException("Data direcotory cannot be crated!");
		}

		DATA_DIR = file;
	}

	public void syncJson(String uuid, String json) throws IOException {
		File userFile = new File(DATA_DIR.getPath() + SEPARATOR + uuid);
		FileWriter writer = new FileWriter(userFile);
		writer.write(json);
		writer.close();
	}

	public String getJson(String uuid) throws IOException {
		File userFile = new File(DATA_DIR.getPath() + SEPARATOR + uuid);

		return Files.readString(Path.of(userFile.getPath()));
	}
}