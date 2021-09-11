package com.message.media.models;

import com.message.media.enums.Team;

import java.time.LocalTime;

public class CurrentBallPossesion {

  public CurrentBallPossesion(){
    this.time = LocalTime.of(0,0, 0,0);
  }

  private Team team;
  private LocalTime time;

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public LocalTime getTime() {
    return time;
  }

  public void setTime(LocalTime time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return "CurrentBallPossesion{" +
        "team=" + team +
        ", time=" + time +
        '}';
  }
}
