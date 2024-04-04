package entity;

import io.avaje.jsonb.Json;

@Json
public record User(Long id, String name) {
}
