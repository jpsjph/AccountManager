package utils

import scala.math.BigDecimal.RoundingMode


object Functions {
  def reduceByKey[K, V](collection: Traversable[Tuple2[K, V]])(implicit num: Numeric[V]) = {
    import num._
    collection
      .groupBy(_._1)
      .map { case (group: K, traversable) => traversable.reduce { (a, b) => (a._1, a._2 + b._2) } }
  }

  def average(iter: List[BigDecimal]): BigDecimal = iter match {
    case Nil => 0
    case _ => (iter.sum / iter.size).setScale(2, RoundingMode.HALF_UP)
  }

  val isValidRolling = (day: Int, interval: Int) => day - interval > 0

  def rollingTimeWindow(day: Int, interval: Int): Int = {
    val diffDays = day - interval
    if (diffDays > 0) diffDays else 0
  }

}
