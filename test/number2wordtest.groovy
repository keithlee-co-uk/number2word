import spock.lang.Specification

class Number2WordTest extends Specification {

    def "A failing test to be sure I have the test environment correct"() {
        def two = 3

        when:
        def four = two + two

        then:
        four == 4

    }
}