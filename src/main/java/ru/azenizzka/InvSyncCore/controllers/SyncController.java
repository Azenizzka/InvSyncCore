package ru.azenizzka.InvSyncCore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.azenizzka.InvSyncCore.services.SyncService;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class SyncController {
	private final SyncService syncService;

	public SyncController(SyncService syncService) {
		this.syncService = syncService;
	}

	@PostMapping("/{uuid}")
	@ResponseBody
	public ResponseEntity<HttpStatus> syncJson(@PathVariable String uuid, @RequestBody String json) throws IOException {
		syncService.syncJson(uuid, json);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{uuid}")
	@ResponseBody
	public ResponseEntity<String> getJson(@PathVariable String uuid) throws IOException {
		String json = syncService.getJson(uuid);
		return ResponseEntity.ok(json);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<String> handleEmptyFileException(IOException exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
	}
}