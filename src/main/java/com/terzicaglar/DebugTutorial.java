package com.terzicaglar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DebugTutorial {

    DropFrame dropFrame;
    Welcome welcome;
    AltKeyUsage altUsage;
    StreamDebugging streamDebugging;
    DropFrameSideEffects dropFrameSideEffects;
    ConditionalBreakpoint conditionalBreakpoint;
    ConditionalBreakpointOtherClass conditionalBreakpointOtherClass;
    ForceReturnAndException forceReturnAndException;
    ExceptionDebugging exceptionDebugging;
    JumpToLinePlugin jumpToLinePlugin;
    HitCount hitCount;












    /**
     * <h1>OneTeg Tech Talks</h1>
     * <h2>#1 Debugging</h2>
     * <h2>Date: September 5 2024</h2>
     * <h2>Presenter: Çağlar Terzi</h2>
     *
     *
     *
     */
    private static class Welcome { }



















    /**
     * <h2>Using Alt (Opt) key for expression evaluation</h2>
     */
    private static class AltKeyUsage {

        public static void main(String[] args) {
            int result = multiply(add(1, 2), add(3, 4));
            log.info("Result: {}", result);
        }

        private static int add(int a, int b){
            return a + b;
        }

        private static int multiply(int a, int b){
            return a * b;
        }
    }













    /**
     * <h2>Stream Debugging</h2>
     */
    private static class StreamDebugging{

        public static void main(String[] args) {
            List<List<String>> list = List.of(
                    List.of("O", "n", "e", "T", "e", "g"),
                    List.of(" "),
                    List.of("r", "o", "c", "k", "s"),
                    List.of("!")
            );

            long distinctLettersCount = list.stream()
                    .flatMap(List::stream)
                    .map(String::toLowerCase)
                    .filter(s -> s.matches("[a-z]"))
                    .distinct()
                    .count();
            log.info("Distinct letters count: {}", distinctLettersCount);
        }

    }









    /**
     * <h2>Drop Frame</h2>
     * Using the "Drop Frame" functionality you can "fall back" to a previous stack frame, in a sense going back in time. This can be helpful to re-enter a function if you missed a critical spot you would like to see again.
     * <p>
     * Source: https://stackoverflow.com/a/2367864/12405221
     */

    public static class DropFrame {

        public static void main(String[] args) {
            dropFrameExample();
        }

        public static void dropFrameExample() {
            try {
                methodA();
            } catch (Exception e) {
                log.error("Error: {}", e.getMessage(), e);
            }
            log.info("End of dropFrameExample");
        }

        public static void methodA() {
            methodB(10, 0);
        }

        public static void methodB(int a, int b) {
            int result = divide(a, b); // This will cause an ArithmeticException
            log.info("Result: {}", result); // This line will not be executed
        }

        public static int divide(int a, int b) {
            return a / b; // Potentially problematic division
        }
    }


    /**
     * <h2>Drop Frame Side Effects</h2>
     */
    private static class DropFrameSideEffects {

        private static final List<String> result = new ArrayList<>();

        public static void main(String[] args) {
            convertAllToLowerCase(List.of("O", "N", "E", "T", "E", "G"));
        }

        private static void convertAllToLowerCase(List<String> o) {
            for (String s : o) {
                convertToLowerCase(s);
            }
            log.info("Result: {}", result);
        }

        private static void convertToLowerCase(String s) {
            result.add(s.toLowerCase());
        }
    }


    /**
     * <h2>Jump to Line Plugin</h2>
     * <ul>
     *     <li>Jump to Line Plugin: https://plugins.jetbrains.com/plugin/14877-jump-to-line</li>
     *     <li>Jump to Line plugin allows you to get to any line of code while debugging</li>
     *     <li>Drag and drop a yellow arrow to the desired place in the gutter</li>
     *     <li>Jumping to lines is possible both forward and backward within a function</li>
     *     <li>Jump to line highlights the lines suitable for jumps in green</li>
     *     <li>IDE will highlight any risky lines in yellow</li>
     */
    private static class JumpToLinePlugin {

        public static void main(String[] args) {
            method1(0);
        }

        private static void method1(int a) {
            if (a == 1) {
                method2(1);
            } else if (a % 2 == 0) {
                method2(2);
            } else {
                method2(-1);
            }
        }

        private static void method2(int a) {
            log.info("Method 2: {}", a);
        }
    }













    /**
     * <h2>Conditional Breakpoint</h2>
     */
    private static class ConditionalBreakpoint {

        public static void main(String[] args) {
            Map<String, String> map = Map.of("key01", "value1", "key02", "value2",
                    "key11", "value1", "key12", "value2",
                    "key03", "value3", "key04", "value4");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                ConditionalBreakpointOtherClass.test(entry.getValue());
            }
        }
    }

    private static class ConditionalBreakpointOtherClass {

        public static void test(String value) {
            log.info("Value: {}", value);
            // Some complex logic
        }
    }











    /**
     * <h2>Force Return</h2>
     * <h2>Force Exception</h2>
     */
    private static class ForceReturnAndException {

        public static void main(String[] args) {
            double result;
            try {
                result = getRandom();
            } catch (Exception e) {
                log.error("Error: {}", e.getMessage(), e);
                return;
            }
            log.info("Result: {}", result);
        }

        private static double getRandom() {
            return Math.random();
        }

    }










    /**
     * <h2>Debugging Exceptions</h2>
     */
    private static class ExceptionDebugging {

        public static void main(String[] args) {
            List<Object> objects = getObjects();
            for (Object object : objects) {
                Integer casted = castToInteger(object);
                log.debug("Casted: {}", casted);
            }
        }

        private static Integer castToInteger(Object object) {
            Integer casted = (Integer) object;
            return casted;
        }

        private static List<Object> getObjects() {
            return List.of(1, 2, 3, "4abc", 5);
        }
    }







    /**
     * <h2>Hit Count</h2>
     * <h2>Evaluate and Log</h2>
     */
    private static class HitCount {

        public static void main(String[] args) {
            int limit = 10;

            // Complex nested for loops
            for (int i = 1; i <= limit; i++) {
                int factor1 = i % 4 == 0 ? 3 : 2;
                int factor2 = (i % 5 + 1) * (i % 6 + 1);

                for (int j = 0; j < factor1 * factor2; j++) {
                    int innerFactor = (i + j) % 7 + 1;

                    for (int k = 0; k < innerFactor * 3; k++) {
                        if ((i + j + k) % 10 == 1) {
                            emptyMethod(); // Called 297 times
                        }
                    }
                }
            }
        }

        private static void emptyMethod() {
            log.debug("Empty method");
        }
    }









    /**
     * <h1>Thanks for listening!</h1>
     *
     * <h2>Sources and Useful Resources</h2>
     * <ul>
     *     <li>IntelliJ Debugger Playlist - https://www.youtube.com/playlist?list=PLPZy-hmwOdEUWF85MuwrKV8YVWLmZW4ZA</li>
     *     <li>Practical Debugging at Scale: Do you Really Know How to Debug Effectively? - https://www.youtube.com/watch?v=l6Rn0dsfK34</li>
     *     <li>Debugging and Profiling - https://missing.csail.mit.edu/2020/debugging-profiling/</li>
     * </ul>
     */
    boolean end = true;

}