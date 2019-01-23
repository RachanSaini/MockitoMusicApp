package com.stackroute.musicApplicationTask1.repository;

import com.stackroute.musicApplicationTask1.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class) //dont run Junit test run with Spring Runner
@DataJpaTest //to get slice of application context...which is related to repository only
public class TrackRepositoryTest {

    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setTrack(){
        track = new Track();
        track.setTrackName("Zara sa");
        track.setComments("KK");
        track.setTrackId(101);
    }

    @After
    public void tearDown(){
        trackRepository.deleteAll();
    }

    @Test
    public void testSaveTrack(){
        trackRepository.save(track);
        Track fetchUser = trackRepository.findtrackByName(track.getTrackName());
        Assert.assertEquals("Zara sa",fetchUser.getTrackName());
    }

    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track();
        testTrack.setTrackName("Maahi");
        testTrack.setComments("Imran");
        trackRepository.save(track);
        Assert.assertNotEquals(testTrack,track);
    }

    @Test
    public void testGetAllTracks(){
        Track track1 = new Track();
        track1.setTrackName("Awarapan");
        track1.setComments("Mustafa");
        trackRepository.save(track1);

        Track track2 = new Track();
        track2.setTrackName("Treat you better");
        track2.setComments("Shawn Mendes");
        trackRepository.save(track2);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Awarapan",list.get(0).getTrackName());

    }

    @Test
    public void deleteTest(){


        trackRepository.delete(track);
        List<Track>  list=trackRepository.findAll();
        Assert.assertEquals(false,list.contains(track));

    }




}
