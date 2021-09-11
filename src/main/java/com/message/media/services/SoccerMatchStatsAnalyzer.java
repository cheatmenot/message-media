package com.message.media.services;

import com.message.media.enums.EventType;
import com.message.media.enums.Team;
import com.message.media.models.CurrentBallPossesion;
import com.message.media.models.EventRecord;
import com.message.media.models.SoccerMatchStatsData;
import com.message.media.models.TeamData;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * analyzer who will process the List of EventRecord to get the summary/computation based on the time given
 *
 * @author rumel.docdoc@eycads.com
 * @since 0.0.1
 */
public class SoccerMatchStatsAnalyzer {

  public SoccerMatchStatsData analyzeData(List<EventRecord> records, String time){
    SoccerMatchStatsData data = new SoccerMatchStatsData();
    TeamData teamA = new TeamData(Team.A);
    TeamData teamB = new TeamData(Team.B);
    CurrentBallPossesion cbp = new CurrentBallPossesion();
    LocalTime requestedTime = CsvReader.parseSoccerTime(time);
    for (EventRecord record : records){
      if (requestedTime.isAfter(record.getTime()) || requestedTime.equals(record.getTime())){
//        System.out.println(record);
        if (record.getTeam() == Team.A){
          processTeam(teamA, teamB, record, cbp);
        } else {
          processTeam(teamB, teamA, record, cbp);
        }
      }
    }
    if (requestedTime.isAfter(cbp.getTime())){
      long diff = cbp.getTime().until(requestedTime, ChronoUnit.SECONDS);
      if (cbp.getTeam() == Team.A){
        teamA.addPositionTime(diff);
      } else {
        teamB.addPositionTime(diff);
      }
    }
    data.setTime(requestedTime);
    data.setTeamA(teamA);
    data.setTeamB(teamB);
    return data;
  }

  public static void processTeam(TeamData teamData, TeamData otherTeam, EventRecord record, CurrentBallPossesion cbp){
    switch (record.getType()){
      case START:
        System.out.println("Start" + record);
        cbp.setTeam(record.getTeam());
        cbp.setTime(record.getTime());
        break;
      case POSSESS:
        System.out.println("Possess" + record);
        if (cbp.getTeam() != record.getTeam()){
          long diff = cbp.getTime().until(record.getTime(), ChronoUnit.SECONDS);
          otherTeam.addPositionTime(diff);
          cbp.setTime(record.getTime());
          cbp.setTeam(record.getTeam());
        }
        break;
      case SHOT:
        System.out.println("Shot" + record);
        teamData.addShot();
        break;
      case SCORE:
        System.out.println("Score" + record);
        teamData.addScore();
        break;
      case BREAK:
        System.out.println("Break" + record);
        long diff = cbp.getTime().until(record.getTime(), ChronoUnit.SECONDS);
        if (cbp.getTeam() == teamData.getTeam()){
          teamData.addPositionTime(diff);
        } else {
          otherTeam.addPositionTime(diff);
        }
        cbp.setTeam(record.getTeam());
        cbp.setTime(record.getTime());
        break;
      case END:
        System.out.println("End" + record);
        long diff2 = cbp.getTime().until(record.getTime(), ChronoUnit.SECONDS);
        if (cbp.getTeam() == teamData.getTeam()){
          teamData.addPositionTime(diff2);
        } else {
          otherTeam.addPositionTime(diff2);
        }
        cbp.setTeam(record.getTeam());
        cbp.setTime(record.getTime());
        break;
      default:
        System.out.println("Unhandled");
        break;
    }
  }

}
