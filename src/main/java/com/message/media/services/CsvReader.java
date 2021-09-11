package com.message.media.services;

import com.message.media.enums.EventType;
import com.message.media.enums.Team;
import com.message.media.models.EventRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * reader for the csv
 *
 * @author rumel.docdoc@eycads.com
 * @since 0.0.1
 */
public class CsvReader {

  public static List<EventRecord> readEventRecords(String path){
    File file = new File(path);
    List<EventRecord> records = new ArrayList<>();
    try(FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr)){
      String line = "";
      while((line = reader.readLine()) != null) {
        if (!line.startsWith("Time")){
          String[] tempArr;
          tempArr = line.split(",");
          EventRecord record = new EventRecord();
          record.setTime(parseSoccerTime(tempArr[0].trim()));
          record.setType(EventType.valueOf(tempArr[1].trim()));
          if (tempArr.length == 3){
            record.setTeam(Team.valueOf(tempArr[2].trim()));
          }
          records.add(record);
//          for(String tempStr : tempArr) {
//            System.out.print(tempStr + " ");
//          }
//          System.out.println();
//          System.out.println(record);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return records;
  }

  public static LocalTime parseSoccerTime(String str){
    String[] tempArr;
    LocalTime time = null;
    tempArr = str.split(":");
    if(tempArr[0] != null && tempArr[1] != null){
      int hours = 0;
      int minutes = 0;
      int seconds = 0;
      int nanoseconds = 0;
      int minutesInput = Integer.parseInt(tempArr[0]);
      if (Integer.parseInt(tempArr[0]) > 59){
        hours = 1;
        minutes = minutesInput - 60;
      } else {
        minutes = minutesInput;
      }
      seconds = Integer.parseInt(tempArr[1]);
      time = LocalTime.of(hours, minutes, seconds, nanoseconds);
    }
    else {
      throw new RuntimeException("Unable to parse to LocalTime with values: " + str);
    }
    return time;
  }

}
