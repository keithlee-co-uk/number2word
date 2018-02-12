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
        n2w.splitToHundreds(integer) == stringList

        where:
        integer | stringList
             1  | ["001"]
            50  | ["050"]
           999  | ["999"]
          1000  | ["000", "001"]
    }
 }
