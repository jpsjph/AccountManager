package models

case class TransactionStats(day: Int, accountId: String, maximum: BigDecimal, average: BigDecimal, ff: BigDecimal = 0, cc: BigDecimal = 0, aa: BigDecimal = 0)

