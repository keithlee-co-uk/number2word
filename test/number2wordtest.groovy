import spock.lang.Specification


class Number2WordTest extends Specification {
    def n2w = new Number2Word()

    /** Zero Rule **/
    def "Value is 0 then the words is 'zero' "() {
        expect:
        n2w.number2Words(digit) == word

        where:
        digit | word
        0 | "zero"

    }

    /** Three Digit Rule **/
    def "value is split into groups of three digits starting from the right-hand side"() {
        expect:
        n2w.splitToThreeDigits(integer) == stringList

        where:
        integer | stringList
             1  | ["001"]
            50  | ["050"]
           999  | ["999"]
          1000  | ["000", "001"]
      56945781  | ["781","945", "056"]
     999999999  | ["999", "999", "999"]
    }


    /* Assumption 1 */
    def "Throw Exception when 'number' is not an integer"() {
        when:
        n2w.number2Words("Fred")

        then:
        thrown(MissingMethodException)

    }

    /* Assumption 2 */
    def "Throw NumberOutOfBounds Exception when 'number' is negative"() {
        when:
        n2w.number2Words(-1)

        then:
        NumberOutOfBounds ex = thrown()
        ex.message == 'Expected a positive integer less than a billion.'
    }


    /* Assumption 3 */
    def "Throw NumberOutOfBounds exception for values over a billion"() {

        when:
        n2w.number2Words(1000000000)

        then:
        NumberOutOfBounds ex = thrown()
        // Alternative syntax: def ex = thrown(InvalidDeviceException)
        ex.message == 'Expected a positive integer less than a billion.'
    }

    /** Hundreds Rule **/
    /*  */
    def "If the hundreds portion of a three-digit group is not zero, the number of hundreds is added as a word."() {
        expect:
        n2w.hundredthsWord(hundredSet) == word

        where:
        word | hundredSet
        ""   | "001"
        "nine"  | "999"
        "seven" | "781"
        "four"  | "499"
    }

 }
