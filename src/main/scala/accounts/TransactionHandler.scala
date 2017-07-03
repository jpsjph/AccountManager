package accounts


object TransactionHandler {
  def main(args: Array[String]) {
    val reader = new FileReader()
    val display = new ScreenDisplay(reader, reader.getFilePath(args))

    display.printTotal
    display.printAvg
    display.printStat(20)
  }
}
