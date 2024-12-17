package com.example.artistapplication.service;

import com.example.artistapplication.model.Artist;
import com.example.artistapplication.model.ArtistRequest;
import com.example.artistapplication.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist createArtist(ArtistRequest artistRequest) {
        Artist artist = new Artist();
        artist.setFirstName(artistRequest.firstName());
        artist.setLastName(artistRequest.lastName());
        return artistRepository.save(artist);
    }

    @Override
    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Optional<Artist> getArtistByID(Long id) {
        return artistRepository.findById(id);
    }

    @Override
    public void deleteArtist(Long id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Artist with ID " + id + " not found");
        }
    }
}
