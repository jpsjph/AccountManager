package utils

import org.scalatest.{FlatSpec, Matchers}
import utils.Functions

class FunctionsTest extends FlatSpec with Matchers {

  behavior of "Functions Test"

  it should "reduce the transaction by type" in {
    val excepted = Map("AA" -> 12, "BB" -> 90, "CC" -> 120)
    val trans = List(("BB", 10), ("CC", 20), ("AA", 12), ("CC", 100), ("BB", 80))
    val sut = Functions.reduceByKey(trans)
    sut should equal(excepted)
  }

  it should "calculate the average" in {
    val exceped = 233.33
    val transactions: List[BigDecimal] = List(100, 200, 400)
    val sut = Functions.average(transactions)
    sut should equal(exceped)
  }

  it should "validate rolling window time" in {
    Functions.isValidRolling(10, 5) should equal(true)
  }

  it should "return false when rolling window time is not possible" in {
    Functions.isValidRolling(4, 5) should equal(false)
  }
  it should "retun a start rolling window time" in {
    val excepted=5
    Functions.rollingTimeWindow(10,5) should equal(excepted)
  }

}
