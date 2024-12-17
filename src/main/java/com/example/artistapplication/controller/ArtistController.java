package com.example.artistapplication.controller;


import com.example.artistapplication.model.Artist;
import com.example.artistapplication.model.ArtistRequest;
import com.example.artistapplication.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping()
    public ResponseEntity<Artist> createPlayList(@RequestBody ArtistRequest artistRequest) {
        Artist createdArtist = artistService.createArtist(artistRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArtist);
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long artistId) {
        return artistService.getArtistByID(artistId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        List<Artist> artists = artistService.getArtists();
        return ResponseEntity.ok(artists);
    }

    @DeleteMapping("/{artistId}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long artistId) {
        try {
            artistService.deleteArtist(artistId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
