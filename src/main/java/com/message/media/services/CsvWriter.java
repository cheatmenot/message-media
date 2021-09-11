package com.message.media.services;

import com.message.media.models.SoccerMatchStatsData;
import com.message.media.models.TeamData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * writes it to a csv file and prints also to console
 *
 * @author rumel.docdoc@eycads.com
 * @since 0.0.1
 */
public class CsvWriter {

  public static String writeToCSV(SoccerMatchStatsData data, String path){
    File csvOutputFile = new File(path);
    String response = null;
    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {

      List<String[]> dataLines = new ArrayList<>();
      dataLines.add(new String[]
          { "Timestamp", "Team", "Possession", "Shot", "Score" });
      TeamData teamA = data.getTeamA();
      dataLines.add(new String[]
          { localTimeToString(data.getTime()), teamA.getTeam().toString(), calculatePercentage(data.getTime(), teamA.getPossessionTime()) + "%",
              String.valueOf(teamA.getShot()), String.valueOf(teamA.getScore())});
      TeamData teamB = data.getTeamB();
      dataLines.add(new String[]
          { localTimeToString(data.getTime()), teamB.getTeam().toString(), calculatePercentage(data.getTime(), teamB.getPossessionTime()) + "%",
              String.valueOf(teamB.getShot()), String.valueOf(teamB.getScore())});
      dataLines.stream()
          .map(CsvWriter::convertToCSV)
          .forEach(pw::println);
      dataLines.stream()
          .map(CsvWriter::convertToCSV)
          .forEach(System.out::println);
      response = dataLines.stream()
          .map(CsvWriter::convertToCSV)
          .collect(Collectors.joining("\n"));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    return response;
  }

  private static String calculatePercentage(LocalTime total, LocalTime actual){
    int actualSeconds = actual.get(ChronoField.SECOND_OF_DAY);
    int totalSeconds = total.get(ChronoField.SECOND_OF_DAY);
    long calc = Math.round(((double) actualSeconds / (double) totalSeconds) * 100);
    return String.format("%02d", calc);
  }

  private static String convertToCSV(String[] data) {
    return String.join(", ", data);
  }

  private static String localTimeToString(LocalTime time){
    int hour = time.getHour();
    int minute = time.getMinute();
    int second = time.getSecond();
    int newMinutes = 0;
    if (hour != 0) {
      newMinutes = minute + (hour * 60);
    } else {
      newMinutes = minute;
    }
    return String.format("%02d", newMinutes) + ":" + String.format("%02d", second);
  }

}
