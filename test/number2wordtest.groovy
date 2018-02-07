import spock.lang.Specification


class Number2WordTest extends Specification {
    def n2w = new Number2Word()

    def "Single digit numbers become words"() {
        expect:
        n2w.word(digit) == word

        where:
        digit | word
        1 | "one"
        2 | "two"
        3 | "three"
        4 | "four"

    }
}