package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import com.sun.org.apache.bcel.internal.generic.DRETURN;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Quiz1 {

    // 1.1 각 취미를 선호하는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz1() throws IOException {
        List<String[]> csvLines = readCsvLines();

        return csvLines.stream()
                .map(line -> line[1].trim())
                .flatMap(h -> Arrays.stream(h.split(":")))
                .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, (oldValue, newValue) -> newValue += oldValue));

    }

    // 1.2 각 취미를 선호하는 정씨 성을 갖는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz2() throws IOException {
        List<String[]> csvLines = readCsvLines();

        return csvLines.stream()
                .filter(line -> line[0].contains("정"))
                .map(line -> line[1].trim())
                .flatMap(h -> Arrays.stream(h.split(":")))
                .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, (oldValue, newValue) -> newValue += oldValue));

    }

    // 1.3 소개 내용에 '좋아'가 몇번 등장하는지 계산하여라.
    public int quiz3() throws IOException {
        List<String[]> csvLines = readCsvLines();
        int cnt = 0;
        String collect = csvLines.stream()
                .map(line -> line[2])
                .filter(str -> str.contains("좋아"))
                .collect(Collectors.joining());

        for (int i = 0; i < collect.length(); i++) {
            if (collect.startsWith("좋아", i)) {
                cnt++;
            }
        }

        return cnt;
    }

    private List<String[]> readCsvLines() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        return csvReader.readAll();
    }

}
