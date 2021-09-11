package com.message.media.models;

import com.message.media.enums.Team;

import java.time.LocalTime;

public class TeamData {

  public TeamData(Team team){
    this.team = team;
    this.possessionTime = LocalTime.of(0, 0, 0, 0);
  }

  private Team team;
  private LocalTime possessionTime;
  private int shot;
  private int score;

  public Team getTeam() {
    return team;
  }

  public LocalTime getPossessionTime() {
    return possessionTime;
  }

  public void setPossessionTime(LocalTime possessionTime) {
    this.possessionTime = possessionTime;
  }

  public void addPositionTime(Long seconds) {
    this.possessionTime = this.possessionTime.plusSeconds(seconds);
  }

  public int getShot() {
    return shot;
  }

  public void setShot(int shot) {
    this.shot = shot;
  }

  public void addShot(){
    this.shot = shot + 1;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void addScore(){
    this.score = score + 1;
  }

  @Override
  public String toString() {
    return "TeamData{" +
        "team=" + team +
        ", possessionTime=" + possessionTime +
        ", shot=" + shot +
        ", score=" + score +
        '}';
  }
}
