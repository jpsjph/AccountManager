package accounts

import java.util.Scanner
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

/*
  def choose = {
    println("Choose from these choices")
    println("-------------------------\n")
    println("1 - Calculate the total ")
    println("2 - Calculate the average")
    println("3 - Calculate statistics for each account")
    println("4 - Quit")
    println()
  }

  def printAll(): Unit = {
    val scanner = new Scanner(System.in)
    val input = scanner.nextInt()

    do{

    }while(input)
      input match {
        case 1 => printTotal
        case 2 => printAvg
        case 3 => {
          println("Please choose day for rolling time")
          println()
          val sc = new Scanner(System.in)
          val day = sc.nextInt()
          printStat(day)
        }
        case 4 => System.exit(0)
        case _ => println("Choice no valid")
      }
  }
*/

}
