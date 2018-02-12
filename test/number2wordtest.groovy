import spock.lang.Specification


class Number2WordTest extends Specification {
    def n2w = new Number2Word()

    def "Value is 0 then the words is 'zero' "() {
        expect:
        n2w.words(digit) == word

        where:
        digit | word
        0 | "zero"

    }

    def "value is split into groups of three digits starting from the right-hand side"() {
        expect:
        n2w.splitToHundreds(integer) == integerList

        where:
        integer | integerList
             1  | ["001"]

    }
 }
