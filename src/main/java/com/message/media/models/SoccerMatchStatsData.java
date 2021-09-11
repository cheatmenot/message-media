package com.message.media.models;

import java.time.LocalTime;

public class SoccerMatchStatsData {

  private LocalTime time;
  private TeamData teamA;
  private TeamData teamB;

  public LocalTime getTime() {
    return time;
  }

  public void setTime(LocalTime time) {
    this.time = time;
  }

  public TeamData getTeamA() {
    return teamA;
  }

  public void setTeamA(TeamData teamA) {
    this.teamA = teamA;
  }

  public TeamData getTeamB() {
    return teamB;
  }

  public void setTeamB(TeamData teamB) {
    this.teamB = teamB;
  }

  @Override
  public String toString() {
    return "SoccerMatchStatsData{" +
        "time=" + time +
        ", teamA=" + teamA +
        ", teamB=" + teamB +
        '}';
  }
}
