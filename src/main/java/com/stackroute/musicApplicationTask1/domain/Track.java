package com.stackroute.musicApplicationTask1.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Track Id")
    private int trackId;
    @ApiModelProperty(notes = "Track name")
    private String trackName;
    @ApiModelProperty(notes = "Track comments")
    private String comments;
}
