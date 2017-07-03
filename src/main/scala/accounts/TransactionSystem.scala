package accounts

import models._
import utils.Functions

object TransactionSystem {
  def totalTransactionByDay(transactions: List[Transaction]): Seq[(Int, BigDecimal)] = {
    val totals = Functions.reduceByKey(transactions.map(x => (x.transactionDay, x.transactionAmount)))
    totals.toSeq.sortBy(_._1)
  }

  def avgTransaction(transactions: List[Transaction]): List[Stats] = {
    val res = transactions.groupBy(rec => (rec.accountId, rec.category)).map {
      case (key, trans) => Stats(key._1, key._2, Functions.average(trans.map(x => x.transactionAmount)))
    }
    res.toList.sortBy(x => x.category)
  }

  def rollingTimeWindowCalc(transactions: List[Transaction], day: Int, interval: Int = 5): List[TransactionStats] = {
    val startDay = Functions.rollingTimeWindow(day, interval)
    if (startDay > 0) {
      val records = transactions.filter(x => x.transactionDay >= startDay && x.transactionDay < day)
      groupByAccount(records)
    } else {
      Nil
    }
  }

  private def groupByAccount(transactions: List[Transaction]) = {
    val records = transactions.groupBy(tran => (tran.transactionDay, tran.accountId)).map {
      case (key: (Int, String), trans: List[Transaction]) => {
        val amounts = trans.map(x => x.transactionAmount)
        val aa = trans.filter(x => x.category == "AA").map(x => x.transactionAmount).sum
        val cc = trans.filter(x => x.category == "CC").map(x => x.transactionAmount).sum
        val ff = trans.filter(x => x.category == "FF").map(x => x.transactionAmount).sum
        TransactionStats(key._1, key._2, amounts.max, Functions.average(amounts), ff, cc, aa)
      }
    }
    records.toList.sortBy(x => (x.day, x.accountId))
  }


}
