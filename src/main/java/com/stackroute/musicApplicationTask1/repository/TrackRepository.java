package com.stackroute.musicApplicationTask1.repository;

import com.stackroute.musicApplicationTask1.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {

     @Query("SELECT t FROM Track t WHERE t.trackName = ?1") // ? how many variables we are passing
     public Track findtrackByName(String trackName); //this method is not present in JPArepository so by providing method name we ask jparepo to provide its normal implementation.

    //but if it has modified implementation then use @Query
}
