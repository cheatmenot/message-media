package com.message.media.models;

import com.message.media.enums.EventType;
import com.message.media.enums.Team;

import java.time.LocalTime;

/**
 * object representation per event row in the csv
 *
 * @author rumel.docdoc@eycads.com
 * @since 0.0.1
 */
public class EventRecord {

  private LocalTime time;

  private EventType type;

  private Team team;

  public LocalTime getTime() {
    return time;
  }

  public void setTime(LocalTime time) {
    this.time = time;
  }

  public EventType getType() {
    return type;
  }

  public void setType(EventType type) {
    this.type = type;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  @Override
  public String toString() {
    return "EventRecord{" +
        "time=" + time +
        ", type=" + type +
        ", team=" + team +
        '}';
  }
}
