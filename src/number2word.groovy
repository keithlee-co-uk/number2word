
class Number2Word {


    def words(int number){
        if(number == 0){
            return "zero"
        }

    }

    def splitToHundreds(int number) {
        def hundredGroups = []
        def hundredGroupsReversed = number.toString().reverse().split("(?<=\\G...)")
        for(numberSet in hundredGroupsReversed) {
            if (numberSet.size() < 3) {
                numberSet = numberSet.padRight(3, "0")
            }
            hundredGroups.add(numberSet.reverse())
        }
        return hundredGroups
    }
}
