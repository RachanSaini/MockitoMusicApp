package com.stackroute.musicApplicationTask1.controller;

import com.stackroute.musicApplicationTask1.domain.Track;
import com.stackroute.musicApplicationTask1.exceptions.TrackAlreadyExistsException;
import com.stackroute.musicApplicationTask1.exceptions.TrackNotFoundException;
import com.stackroute.musicApplicationTask1.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Music Application",description ="this is a app.")
public class TrackController {

    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }


    @ApiOperation(value = "Saves new Track")
    @ApiResponses(
            value = {
                    @ApiResponse(code=100,message="well done! Track saved"),
                    @ApiResponse(code=404,message="nope! save track didnt work.")

            }
    )
    @PostMapping("/track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<>(trackService.saveTrack(track),HttpStatus.CREATED);
        return responseEntity;
    }

    @ApiOperation(value = "Returns Track by Id")
    @GetMapping("track")
    public ResponseEntity<?> getTrackbyid(@RequestParam int trackId)throws TrackNotFoundException{
        ResponseEntity<?> responseEntity;

            Track track=trackService.getTrack(trackId);
            responseEntity=new ResponseEntity<>(track,HttpStatus.OK);

        return responseEntity;
    }

    //calling trackByName


    @ApiOperation(value = "Returns Track by Name")
    @GetMapping("track{trackName}")
    public ResponseEntity<?> getTrackByName(@PathVariable("trackName") String trackName){
        Track track=trackService.findtrackByName(trackName);
        return new ResponseEntity<Track>(track,HttpStatus.OK);
    }

    @ApiOperation(value = "Returns all Tracks")
    @GetMapping("/tracks")
    public ResponseEntity<?> getAllTracks(){
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }

    @ApiOperation(value = "updates Track")
    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        trackService.updateTrack(track);
        return new ResponseEntity(track, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns Track by Id")
    @DeleteMapping("/track")
    public ResponseEntity<?> deleteTrack(@RequestParam int id) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        trackService.deleteTrack(id);
        return new ResponseEntity("deleted successfully",HttpStatus.OK);
    }
}
