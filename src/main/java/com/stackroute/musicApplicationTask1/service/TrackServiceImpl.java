package com.stackroute.musicApplicationTask1.service;

import com.stackroute.musicApplicationTask1.domain.Track;
import com.stackroute.musicApplicationTask1.exceptions.EmptyFieldException;
import com.stackroute.musicApplicationTask1.exceptions.TrackAlreadyExistsException;
import com.stackroute.musicApplicationTask1.exceptions.TrackNotFoundException;
import com.stackroute.musicApplicationTask1.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }


    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException("Track already exists.");
        }
        Track saveTrack = trackRepository.save(track);
        return saveTrack;
    }

    //abtract method track by name
    @Override
    public Track findtrackByName(String trackName){
        Track trackByName = trackRepository.findtrackByName(trackName);
        return trackByName;
    }

    @Override
    public Track getTrack(int trackId) throws TrackNotFoundException{
        if(!(trackRepository.existsById(trackId))){
            throw new TrackNotFoundException("track does not exist");
        }
        return trackRepository.findById(trackId).get();

    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll() ;
    }

    @Override
    public Track updateTrack(Track track) throws TrackNotFoundException {
        if(trackRepository.existsById(track.getTrackId())){
           track.setComments(track.getComments());
           track.setTrackName(track.getTrackName());
           return trackRepository.save(track);
        }else
            throw new TrackNotFoundException("Track doesn't exists.");
    }

    @Override
    public boolean deleteTrack(int id) throws TrackNotFoundException {
        if(trackRepository.existsById(id)) {
            trackRepository.deleteById(id);
            return true;
        }else
            throw new TrackNotFoundException("Track doesn't exists.");
    }

}
