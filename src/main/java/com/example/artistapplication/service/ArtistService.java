package com.example.artistapplication.service;


import com.example.artistapplication.model.Artist;
import com.example.artistapplication.model.ArtistRequest;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    Artist createArtist(ArtistRequest artistRequest);
    List<Artist> getArtists();

    Optional<Artist> getArtistByID(Long id);

    void deleteArtist(Long id);
}
