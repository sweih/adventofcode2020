package de.beachboys.aoc2020;

import de.beachboys.Day;
import de.beachboys.DayTest;
import de.beachboys.IOHelper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;import java.util.List;
import java.util.stream.Stream;

public class Day04Test extends DayTest {

    private final Day day = new Day04();

    private static Stream<Arguments> provideTestDataForPart1() {
        return Stream.of(
                Arguments.of(List.of("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
                        "byr:1937 iyr:2017 cid:147 hgt:183cm",
                        "",
                        "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
                        "hcl:#cfa07d byr:1929",
                        "",
                        "hcl:#ae17e1 iyr:2013",
                        "eyr:2024",
                        "ecl:brn pid:760753108 byr:1931",
                        "hgt:179cm",
                        "",
                        "hcl:#cfa07d eyr:2025 pid:166559648",
                        "iyr:2011 ecl:brn hgt:59in"), "2", null)

        );
    }

    private static Stream<Arguments> provideTestDataForPart2() {
        return Stream.of(
                Arguments.of(List.of("inputlines"), 2, null)

        );
    }

    @ParameterizedTest
    @MethodSource("provideTestDataForPart1")
    public void testPart1(List<String> input, Object expected, IOHelper io) {
        testPart1(this.day, input, expected, io);
    }

    @ParameterizedTest
    @MethodSource("provideTestDataForPart2")
    public void testPart2(List<String> input, Object expected, IOHelper io) {
        testPart2(this.day, input, expected, io);
    }

}