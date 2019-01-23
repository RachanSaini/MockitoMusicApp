package com.stackroute.musicApplicationTask1.service;

import com.stackroute.musicApplicationTask1.domain.Track;
import com.stackroute.musicApplicationTask1.exceptions.TrackAlreadyExistsException;
import com.stackroute.musicApplicationTask1.exceptions.TrackNotFoundException;
import com.stackroute.musicApplicationTask1.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {

    Track track;

    @Mock
    TrackRepository trackRepository;

    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list = null;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setTrackName("Crazy");
        track.setComments("Good Song");
        list = new ArrayList<>();
        list.add(track);
    }

    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track)any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);

        verify(trackRepository,times(1)).save(track);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveUserTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track)any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertNotEquals(track,savedTrack);
        throw new TrackAlreadyExistsException("Track already exists");

    }

    @Test
    public void getAllTracks(){
        trackRepository.save(track);

        when(trackRepository.findAll()).thenReturn(list);
        List<Track> trackList = trackService.getAllTracks();
        Assert.assertEquals(list,trackList);
    }

    @Test
    public void deleteTrack() throws TrackNotFoundException {

        when(trackRepository.existsById((Integer)any())).thenReturn(true);

        //Track deletedTrack=TrackService.getTrackById(track.getId());
        boolean result = trackService.deleteTrack(track.getTrackId());

        verify(trackRepository, times(1)).deleteById((Integer)any());

        Assert.assertEquals(true,result);
    }

    @Test
    public void updateTrack() throws TrackNotFoundException {
        when(trackRepository.save((Track)any())).thenReturn(track);
        Track updatedTrack = trackService.updateTrack(track);
        Assert.assertNotEquals(track,updatedTrack);
        throw new TrackNotFoundException("Track doesn't exists");
    }
}
