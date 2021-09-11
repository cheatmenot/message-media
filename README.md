### Assumptions
For CSV Input:
* a valid csv
* time are incrementing from 00:00 to 90:00, and time must be in equal or in between these values
* POSSES must be explicitly present in the csv after when scoring or break when there is change of ball possession like:
```
05:00, SHOT, A
05:00, SCORE, A
05:00, POSSESS, B

45:00, BREAK,
45:00, START, B
```

### Approach
1. CsvReader (com.message.media.services.CsvReader) reader of the csv accepts path then returns a 
   List of EventRecord Object after parsing
2. EventRecord (com.message.media.models.EventRecord) is the object representation per event row in the csv
3. SoccerMatchStatsAnalyzer (com.message.media.services.SoccerMatchStatsAnalyzer) analyzer who will process the List of EventRecord to get the summary/computation based on the time given
4. CsvWriter (com.message.media.services.CsvWriter) writes it to a csv file and prints also to console

### Testing
run test on SoccerMatchStatsAnalyzerTest (com.message.media.services.SoccerMatchStatsAnalyzerTest)
1. `analyzeDataTest1` example from the given docs - input: `sample1.csv`, output: `result1.csv`
2. `analyzeDataTest2` example from the given docs - input: `sample1.csv`, output: `result2.csv`
3. `analyzeDataTest3AlternateBallPossession` alternate ball possession per 5 mins - input `sample2.csv`, output: `result3.csv`
4. `analyzeDataTest4` score, ball possession to opposite - input: `sample3.csv`, outpur: `result4.csv`