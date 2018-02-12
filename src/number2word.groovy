public class Number2Word {


    def words(int number){
        if(number == 0){
            return "zero"
        }

    }

    def splitToHundreds(int number) {
        def hundredGroups = []
        def stringNUmber = number.toString()
        hundredGroups.add(stringNUmber.padLeft(3, "0"))

        return hundredGroups
    }
}
