import spock.lang.Specification

class AssumptionsTest extends Specification {
    def n2w = new Number2Word()

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

}

class ZeroRuleTest extends Specification {
    def n2w = new Number2Word()

    def "Value is 0 then the words is 'zero' "() {
        expect:
        n2w.number2Words(digit) == word

        where:
        digit | word
        0     | "zero"

    }
}

class ThreeDigitRuleTest extends Specification {
    def n2w = new Number2Word()

    def "value is split into groups of three digits starting from the right-hand side"() {
        expect:
        n2w.splitToThreeDigits(integer) == stringList

        where:
        integer   | stringList
        1         | ["001"]
        50        | ["050"]
        999       | ["999"]
        1000      | ["000", "001"]
        56945781  | ["781", "945", "056"]
        999999999 | ["999", "999", "999"]
    }
}

class HundredsRuleTest extends Specification {
    def n2w = new Number2Word()

    def "If the hundreds portion of a three-digit group is not zero, the number of hundreds is added as a word."() {
        /* If the three-digit group is not exactly divisible by one hundred, the text 'hundred and' is appended*/
        expect:
        n2w.hundredthsWord(hundredSet) == word

        where:
        word                | hundredSet
        ""                  | "001"
        "nine hundred and"  | "999"
        "seven hundred and" | "781"
        "four hundred and"  | "499"
    }

    def "If the three-digit group is exactly divisible by one hundred, the text 'hundred' is appended"() {
        expect:
        n2w.hundredthsWord(hundredSet) == words

        where:
        words           | hundredSet
        "one hundred"   | "100"
        "two hundred"   | "200"
        "three hundred" | "300"
        "nine hundred"  | "900"
    }
}

class TensRuleTest extends Specification {
    def n2w = new Number2Word()

    def "If the tens section of a three-digit group is two or higher, the appropriate '-ty' word is added to the text"() {
        expect:
        n2w.tenWord(hundredSet) == words

        where:
        words    | hundredSet
        "twenty" | "020"
        "thirty" | "030"
        "ninety" | "090"
    }

    def "and followed by the name of the third digit (unless the third digit is a zero, which is ignored)."() {
        expect:
        n2w.tenWord(hundredSet) == words

        where:
        words         | hundredSet
        "forty"       | "040"
        "fifty five"  | "055"
        "sixty seven" | "067"

    }

    def "If the tens and the units are both zero, no text is added."() {
        expect:
        n2w.tenWord(hundredSet) == words

        where:
        words    | hundredSet
        ""       | "000"
    }

    def "For any other value, the name of the one or two-digit number is added as a special case."() {
        /* Tens digit is 1 or 0 */
        expect:
        n2w.tenWord(hundredSet) == words

        where:
        words      | hundredSet
        "five"     | "005"
        "twelve"   | "012"
        "nineteen" | "019"

    }
}

class RecombinationRuleTest extends Specification {
    def n2w = new Number2Word()

    /* When recombining the translated three-digit groups, */
    def "each group except the last is followed by a large number name"() {
        expect:
        n2w.recombine(ThreeDigitGroups) == words

        where:
        ThreeDigitGroups | words
        ["one hundred and five", "one"] | "one thousand one hundred and five"
        ["and twenty one", "thirty"] | "thirty thousand and twenty one"
        ["seven hundred and eighty one", "nine hundred and forty five", "fifty six"] | "fifty six million nine hundred and forty five thousand seven hundred and eighty one"
    }

    /*
    def "unless the group is blank and therefore not included at all. One exception is
    **/
    def "when the final group does not include any hundreds and there is more than one non-blank group. In this case, the final part is prepended with 'and'."() {
        expect:
        n2w.recombine(ThreeDigitGroups) == words

        where:
        ThreeDigitGroups | words
        ["one", "nine hundred and forty five", "fifty six"] | "fifty six million nine hundred and forty five thousand and one"
    }
}

