package com.message.media.services;

import com.message.media.models.EventRecord;
import com.message.media.models.SoccerMatchStatsData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

public class SoccerMatchStatsAnalyzerTest {

  @Test
  public void analyzeDataTest1(){
    String path = "src/main/resources/sample1.csv";
    List<EventRecord> records = CsvReader.readEventRecords(path);
    SoccerMatchStatsAnalyzer analyzer = new SoccerMatchStatsAnalyzer();
    SoccerMatchStatsData data = analyzer.analyzeData(records, "00:11");
    String resultPath = "src/main/resources/result1.csv";
    System.out.println("");
    String actual = CsvWriter.writeToCSV(data, resultPath);
    String expected = "Timestamp, Team, Possession, Shot, Score\n" +
        "00:11, A, 91%, 0, 0\n" +
        "00:11, B, 09%, 0, 0";
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void analyzeDataTest2(){
    String path = "src/main/resources/sample1.csv";
    List<EventRecord> records = CsvReader.readEventRecords(path);
    SoccerMatchStatsAnalyzer analyzer = new SoccerMatchStatsAnalyzer();
    SoccerMatchStatsData data = analyzer.analyzeData(records, "02:21");
    String resultPath = "src/main/resources/result2.csv";
    System.out.println("");
    String actual = CsvWriter.writeToCSV(data, resultPath);
    String expected = "Timestamp, Team, Possession, Shot, Score\n" +
        "02:21, A, 65%, 2, 1\n" +
        "02:21, B, 35%, 0, 0";
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void analyzeDataTest3AlternateBallPossession(){
    String path = "src/main/resources/sample2.csv";
    List<EventRecord> records = CsvReader.readEventRecords(path);
    SoccerMatchStatsAnalyzer analyzer = new SoccerMatchStatsAnalyzer();
    SoccerMatchStatsData data = analyzer.analyzeData(records, "90:00");
    String resultPath = "src/main/resources/result3.csv";
    System.out.println("");
    String actual = CsvWriter.writeToCSV(data, resultPath);
    String expected = "Timestamp, Team, Possession, Shot, Score\n" +
        "90:00, A, 50%, 0, 0\n" +
        "90:00, B, 50%, 0, 0";
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void analyzeDataTest4(){
    String path = "src/main/resources/sample3.csv";
    List<EventRecord> records = CsvReader.readEventRecords(path);
    SoccerMatchStatsAnalyzer analyzer = new SoccerMatchStatsAnalyzer();
    SoccerMatchStatsData data = analyzer.analyzeData(records, "45:00");
    String resultPath = "src/main/resources/result4.csv";
    System.out.println("");
    String actual = CsvWriter.writeToCSV(data, resultPath);
    String expected = "Timestamp, Team, Possession, Shot, Score\n" +
        "45:00, A, 31%, 1, 1\n" +
        "45:00, B, 69%, 0, 0";
    Assertions.assertEquals(expected, actual);
  }
}
