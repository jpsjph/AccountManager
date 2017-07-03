package accounts

import models._
import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

class TransactionSystemTest extends FlatSpec with Matchers with BeforeAndAfterEach {

  var transactions: List[Transaction] = _

  override def beforeEach() = {
    transactions = List(Transaction("T0002", "A5", 9, "BB", 677.89),
      Transaction("T0001", "A27", 9, "AA", 338.11),
      Transaction("T0003", "A27", 9, "AA", 499.86),
      Transaction("T0004", "A29", 9, "AA", 801.81),
      Transaction("T0005", "A19", 9, "BB", 14.42),
      Transaction("T0007", "A29", 9, "FF", 439.49),
      Transaction("T000", "A46", 9, "FF", 476.88),
      Transaction("T0008", "A49", 9, "DD", 848.90),
      Transaction("T0009", "A47", 9, "BB", 400.42),
      Transaction("T00010", "A23", 9, "BB", 416.36),
      Transaction("T00011", "A23", 11, "BB", 662.98),
      Transaction("T00012", "A2", 11, "DD", 775.37),
      Transaction("T00013", "A33", 11, "BB", 185.40))
  }

  behavior of "TransactionSystem Test"

  it should "calculate the total transaction value" in {
    val excepted = Vector((9, 4914.14), (11, 1623.75))
    val trans = TransactionSystem.totalTransactionByDay(transactions)
    trans.size should equal(2)
    trans should equal(excepted)
  }

  it should "calculate the average value of transaction value" in {

    val statTrans = List(Transaction("T0002", "A23", 10, "BB", 677.89),
      Transaction("T0001", "A27", 10, "AA", 338.11),
      Transaction("T0003", "A27", 10, "AA", 499.86),
      Transaction("T0004", "A27", 10, "AA", 801.81),
      Transaction("T00010", "A23", 10, "BB", 416.36),
      Transaction("T00011", "A23", 11, "BB", 662.98))

    val excepted = List(Stats("A27", "AA", 546.59), Stats("A23", "BB", 585.74))
    val trans = TransactionSystem.avgTransaction(statTrans)
    trans.size should equal(2)
    trans should equal(excepted)
  }

  it should "calculate statistics for each account" in {
    val excepted = Vector((9, 4914.14), (11, 1623.75))
    val trans = TransactionSystem.rollingTimeWindowCalc(transactions, 10, 5)
    trans.size should equal(8)
  }

  it should "return empty when time window is not valid" in {
    val excepted = Vector((9, 4914.14), (11, 1623.75))
    val trans = TransactionSystem.rollingTimeWindowCalc(transactions, 7, 5)
    trans should equal(Nil)
  }
}
