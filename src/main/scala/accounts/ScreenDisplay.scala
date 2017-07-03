package accounts
class ScreenDisplay(reader: ContentReader, path: String) {

  lazy val transactions = reader.getFileContent(path)

  def printTotal = {
    val records = TransactionSystem.totalTransactionByDay(transactions)
    records.foreach(p => println(s"Day: ${p._1} - Total:${p._2}"))
  }

  def printAvg = {
    val records = TransactionSystem.avgTransaction(transactions)
    records.foreach(p => println(s"AccountId: ${p.accountId} - Category:${p.category} - Average:${p.avg}"))
  }

  def printStat(day: Int) = {
    val records = TransactionSystem.rollingTimeWindowCalc(transactions, day)
    records.foreach(p => println(s"Day: ${p.day} - Accountid:${p.accountId} - Maximum: ${p.maximum} - Average: ${p.average} - AA Total:${p.aa} - CC Total:${p.cc} - FF Total:${p.ff}"))
  }
}
