package accounts

import models.Transaction

import scala.io.Source


trait ContentReader {
  def getFilePath(items: Array[String]): String

  def getFileContent(path: String): List[Transaction]
}

class FileReader extends ContentReader {

  override def getFilePath(items: Array[String]): String = {
    if (items.isEmpty)
      throw new IllegalArgumentException("No filename given to index.")
    items.head
  }

  override def getFileContent(path: String): List[Transaction] = {
    val source = Source.fromFile(path)
    try {
      val transactionLines = source.getLines().drop(1)
      transactionLines.map { line =>
        val split = line.split(',')
        Transaction(split(0), split(1), split(2).toInt, split(3), BigDecimal(split(4)))
      }.toList
    } finally {
      source.close()
    }
  }

}
